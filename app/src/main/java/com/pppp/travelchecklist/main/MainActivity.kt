package com.pppp.travelchecklist.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.lifecycle.Observer
import com.pppp.travelchecklist.BuildConfig
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
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
import com.pppp.travelchecklist.settings.dialog.AppTheme
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
        (applicationContext as App).appComponent.mainSubComponentFactory().create(this).inject(this)
        setUpViews()
        viewStates.states.observe(this, Observer { render(it) })
        transientEventsProducer.transientEvents.observe(this, Observer { onTransientEventReceived(it) })
        if (savedInstanceState == null) {
            emit(MainViewIntent.GetLatest(intent?.data?.pathSegments ?: emptyList()))
        }
        sendBroadcast()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        emit(MainViewIntent.GetLatest(intent?.data?.pathSegments ?: emptyList()))
    }

    private fun setUpViews() {
        fab.setOnClickListener {
            findAddedFragment<CheckListFragment>(R.id.container)?.apply { addCategory() } ?: emit(MainViewIntent.GoMakeNewList)
        }
        setSupportActionBar(bottom_bar)
        button.setOnClickListener { emit(MainViewIntent.GoMakeNewList) }
    }

    override fun onCreateOptionsMenu(menu: Menu) = menuVisualizer.onCreateOptionsMenu(menu, this)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> emit(MainViewIntent.NavMenuOpenSelected)
            R.id.add -> AddNewListBottomConfirmationFragment().show(supportFragmentManager, AddNewListBottomConfirmationFragment.ADD_LIST)
            R.id.action_show_hide_checked -> emit(MainViewIntent.OnSettingChanged(item.itemId))
            R.id.delete -> DeleteBottomConfirmationFragment().show(supportFragmentManager, DeleteBottomConfirmationFragment.DELETE_LIST)
            R.id.settings -> emit(MainViewIntent.SettingsSelected)
        }
        return true
    }

    private fun render(viewState: MainViewState) {
        menuVisualizer.updateMenu(viewState.settings)
        setAppTheme(viewState.settings.appTheme)
        return when (viewState) {
            is MainViewState.NoListsPresent -> onNoListsPresent()
            is MainViewState.Content -> onContentPresent()
            is MainViewState.Loading -> onLoading()
            is MainViewState.None -> Unit
            is MainViewState.LatestListNotAvailable -> onLatestListNotAvailable()
        }
    }

    private fun setAppTheme(appTheme: AppTheme) {
//        when (appTheme) {
//            AppTheme.DARK -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
//            AppTheme.LIGHT -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
//            AppTheme.DEFAULT -> {
//                if (BuildConfig.VERSION_CODE > android.os.Build.VERSION_CODES.P) {
//                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_AUTO_BATTERY)
//                }
//            }
//        }
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

    private fun removeListFragment() = navigator.removeListFragment(this)

    private fun onTransientEventReceived(transientEvent: MainTransientEvent) = when (transientEvent) {
        is MainTransientEvent.OpenNavMenu -> navigator.openNavMenu(this, transientEvent.userChecklists, transientEvent.lastList)
        is MainTransientEvent.GoToCreateNewList -> navigator.startCreateChecklistActivity(this)
        is MainTransientEvent.GoToList -> navigator.goToList(this, transientEvent.listId)
        is MainTransientEvent.Error -> onError(transientEvent.message)
        is MainTransientEvent.GoToSettings -> navigator.goToSettings(this)
    }

    private fun emit(mainViewAction: MainViewIntent) {
        actions.accept(mainViewAction)
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
