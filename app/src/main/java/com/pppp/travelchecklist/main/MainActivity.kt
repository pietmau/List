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
import com.pppp.travelchecklist.main.viewmodel.MainViewModel
import com.pppp.travelchecklist.main.viewmodel.ErrorCallback
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.utils.findFragmentById
import com.pppp.travelchecklist.list.view.ViewCheckListFragment
import com.pppp.travelchecklist.main.model.Navigator
import com.pppp.travelchecklist.main.viewmodel.MainViewState
import com.pppp.travelchecklist.newlist.NewListActivity.Companion.CHECKLIST_ID
import com.pppp.travelchecklist.newlist.NewListActivity.Companion.CREATE_NEW_LIST
import com.pppp.travelchecklist.utils.showConfirmationDialog
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ErrorCallback, BottomNavigationDrawerFragment.BottomNavigationItemListener {
    @Inject
    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var transientEventsProducer: TransientEventsProducer<MainViewModel.MainTransientEvent>
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as App).appComponent.with(MainModule(this)).inject(this)
        setUpViews()
        viewModel.states.observe(this, Observer { render(it) })
        transientEventsProducer.transientEvents.observe(this, Observer { onTransientEventReceived(it) })
        if (savedInstanceState == null) {
            emit(MainViewModel.MainViewAction.GetLatestListVisited)
        }
    }

    private fun setUpViews() {
        fab.setOnClickListener {
            onFabClicked()
        }
        setSupportActionBar(bottom_bar)
        button.setOnClickListener { emit(MainViewModel.MainViewAction.GoMakeNewList) }
        collapsing.isTitleEnabled = false
    }

    private fun onFabClicked() {
        val frag = findFragmentById<ViewCheckListFragment>(R.id.container)
        if (frag != null && frag.isAdded) {
            frag.addCategory()
        } else {
            emit(MainViewModel.MainViewAction.GoMakeNewList)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> emit(MainViewModel.MainViewAction.NavMenuOpenSelected)
            R.id.add -> showConfirmationDialog({ emit(MainViewModel.MainViewAction.GoMakeNewList) })
        }
        return true
    }

    private fun render(viewState: MainViewState) = when (viewState) {
        is MainViewState.Empty -> onNoListPresent()
        is MainViewState.Content -> onContentPresent()
        is MainViewState.Loading -> onLoading()
    }

    private fun onLoading() {
        loading_content_error.loading()
        bottom_bar.navigationIcon = null
    }

    private fun onContentPresent() {
        loading_content_error.hide()
        bottom_bar.setNavigationIcon(R.drawable.ic_baseline_menu_24px)
    }

    private fun onNoListPresent() {
        loading_content_error.empty()
        bottom_bar.navigationIcon = null
    }

    private fun onTransientEventReceived(transientEvent: MainViewModel.MainTransientEvent) = when (transientEvent) {
        is MainViewModel.MainTransientEvent.OpenNavMenu -> openNavMenu(transientEvent.userChecklists, transientEvent.lastList)
        is MainViewModel.MainTransientEvent.GoToCreateNewList -> navigator.startCreateChecklistActivity(this)
        is MainViewModel.MainTransientEvent.GoToList -> navigator.goToList(this, transientEvent.listId)
        is MainViewModel.MainTransientEvent.Error -> onError(transientEvent.message)
    }

    private fun emit(mainViewAction: MainViewModel.MainViewAction) {
        viewModel.accept(mainViewAction)
    }

    private fun openNavMenu(checkLists: List<TravelCheckListImpl>, lastList: String?) {
        BottomNavigationDrawerFragment.newInstance(checkLists, lastList).show(supportFragmentManager, BottomNavigationDrawerFragment.TAG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CREATE_NEW_LIST && resultCode == RESULT_OK) {
            val checkListId = data?.extras?.getString(CHECKLIST_ID) ?: return
            emit(MainViewModel.MainViewAction.NewListGenerated(checkListId))
        }
    }

    override fun onNavItemSelected(navigationAction: BottomNavigationDrawerFragment.NavigationAction) {
        emit(navigator.map(navigationAction))
    }

    override fun onError(text: String) {
        Snackbar.make(root, text, Snackbar.LENGTH_LONG).show()
    }

    fun setListTitle(name: String, subTitle: String) {
        toolbar.title = name
        toolbar.subtitle = subTitle
    }
}
