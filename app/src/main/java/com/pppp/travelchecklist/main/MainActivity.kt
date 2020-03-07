package com.pppp.travelchecklist.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.viewmodel.ErrorCallback
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.confirmation.AddNewListBottomConfirmationFragment
import com.pppp.travelchecklist.confirmation.DeleteBottomConfirmationFragment
import com.pppp.travelchecklist.list.view.CheckListFragment
import com.pppp.travelchecklist.main.model.Navigator
import com.pppp.travelchecklist.main.view.MenuVisualizer
import com.pppp.travelchecklist.navigation.BottomNavigationDrawerFragment
import com.pppp.travelchecklist.createlist.NewListActivity.Companion.CHECKLIST_ID
import com.pppp.travelchecklist.createlist.NewListActivity.Companion.CREATE_NEW_LIST
import com.pppp.travelchecklist.notifications.bootreceiver.BootReceiver
import com.pppp.travelchecklist.utils.findAddedFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.UnsupportedOperationException
import javax.inject.Inject

const val ACTION = "com.pppp.travelchecklist.pppp.SAVE"

class MainActivity : AppCompatActivity(), ErrorCallback, BottomNavigationDrawerFragment.BottomNavigationItemListener {
    @Inject
    lateinit var viewStates: ViewStatesProducer<MainViewState>
    @Inject
    lateinit var actions: ViewActionsConsumer<MainViewIntent>
    @Inject
    lateinit var transientEventsProducer: TransientEventsProducer<MainTransientEvent>
    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var menuVisualizer: MenuVisualizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as App).appComponent.with(MainModule(this)).inject(this)
        setUpViews()
        viewStates.states.observe(this, Observer { render(it) })
        transientEventsProducer.transientEvents.observe(this, Observer { onTransientEventReceived(it) })
        if (savedInstanceState == null) {
            val path = intent?.data?.pathSegments ?: emptyList()
            emit(MainViewIntent.GetLatest(path))
        }
        sendBroadcast()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        emit(MainViewIntent.GetLatest(intent?.data?.pathSegments ?: emptyList()))
    }

    private fun setUpViews() {
        fab.setOnClickListener {
            getCheckListFragment()?.apply { addCategory() } ?: emit(MainViewIntent.GoMakeNewList)
        }
        setSupportActionBar(bottom_bar)
        button.setOnClickListener { emit(MainViewIntent.GoMakeNewList) }
    }

    override fun onCreateOptionsMenu(menu: Menu?) = menuVisualizer.onCreateOptionsMenu(menu, this)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> emit(MainViewIntent.NavMenuOpenSelected)
            R.id.add -> AddNewListBottomConfirmationFragment().show(supportFragmentManager, AddNewListBottomConfirmationFragment.ADD_LIST)
            R.id.action_show_hide_checked -> emit(MainViewIntent.OnSettingChanged(item.itemId))
            R.id.delete -> DeleteBottomConfirmationFragment().show(supportFragmentManager, DeleteBottomConfirmationFragment.DELETE_LIST)
        }
        return true
    }

    private fun render(viewState: MainViewState) {
        menuVisualizer.updateMenu(viewState.settings)
        return when (viewState) {
            is MainViewState.NoListsPresent -> onNoListsPresent()
            is MainViewState.Content -> onContentPresent()
            is MainViewState.Loading -> onLoading()
            is MainViewState.None -> Unit
            is MainViewState.LatestListNotAvailable -> onLatestListNotAvailable()
        }
    }

    private fun onLatestListNotAvailable() {
        removeListFragment()
        loading_content_error.empty()
        bottom_bar.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_menu_24px, theme)
    }

    private fun onLoading() {
        removeListFragment()
        loading_content_error.loading()
    }

    private fun onContentPresent() {
        loading_content_error.content()
        bottom_bar.navigationIcon = resources.getDrawable(R.drawable.ic_baseline_menu_24px, theme)
    }

    private fun onNoListsPresent() {
        removeListFragment()
        loading_content_error.empty()
        bottom_bar.navigationIcon = null
    }

    private fun removeListFragment() {
        navigator.removeListFragment(this)
    }

    private fun onTransientEventReceived(transientEvent: MainTransientEvent) = when (transientEvent) {
        is MainTransientEvent.OpenNavMenu -> openNavMenu(transientEvent.userChecklists, transientEvent.lastList)
        is MainTransientEvent.GoToCreateNewList -> navigator.startCreateChecklistActivity(this)
        is MainTransientEvent.GoToList -> navigator.goToList(this, transientEvent.listId)
        is MainTransientEvent.Error -> onError(transientEvent.message)
    }

    private fun emit(mainViewAction: MainViewIntent) {
        actions.accept(mainViewAction)
    }

    private fun openNavMenu(checkLists: List<TravelCheckListImpl>, lastList: String?) {
        BottomNavigationDrawerFragment.newInstance(checkLists, lastList).show(supportFragmentManager, BottomNavigationDrawerFragment.TAG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_LIST && resultCode == RESULT_OK) {
            val checkListId = data?.extras?.getString(CHECKLIST_ID) ?: return
            emit(MainViewIntent.NewListGenerated(checkListId))
        }
    }

    override fun onNavItemSelected(navigationAction: BottomNavigationDrawerFragment.NavigationAction) {
        emit(navigator.map(navigationAction))
    }

    override fun onError(text: String) {
        Snackbar.make(root, text, Snackbar.LENGTH_LONG).show()
    }

    fun onListNotAvailable() {
        emit(MainViewIntent.OnNoListFound)
    }

    private fun getCheckListFragment() = findAddedFragment<CheckListFragment>(R.id.container)

    fun sendBroadcast() {
        sendBroadcast(Intent(this, BootReceiver::class.java).apply {
            action = ACTION
        })
    }

    fun onDialogConfirmed(tag: String): Unit = when (tag) {
        AddNewListBottomConfirmationFragment.ADD_LIST -> emit(MainViewIntent.GoMakeNewList)
        DeleteBottomConfirmationFragment.DELETE_LIST -> emit(MainViewIntent.DeleteCurrentList)
        else -> throw UnsupportedOperationException()
    }

}
