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
import com.pppp.travelchecklist.list.view.ViewCheckListFragment
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.viewmodel.MainViewModel
import com.pppp.travelchecklist.main.viewmodel.ErrorCallback
import com.pppp.travelchecklist.TransientEvents
import com.pppp.travelchecklist.main.model.Navigator
import com.pppp.travelchecklist.newlist.NewListActivity
import com.pppp.travelchecklist.newlist.NewListActivity.Companion.CHECKLIST_ID
import com.pppp.travelchecklist.newlist.NewListActivity.Companion.CREATE_NEW_LIST
import com.pppp.travelchecklist.showConfirmationDialog
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ErrorCallback, BottomNavigationDrawerFragment.BottomNavigationItemListener {
    @Inject
    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var transientEvents: TransientEvents<MainViewModel.TransientEvent>
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as App).appComponent.with(MainModule(this)).inject(this)
        setupViews()
        setupViewModel()
        if (savedInstanceState == null) {
            emit(MainViewModel.ViewEvent.GetLatestListVisited)
        }
    }

    private fun setupViewModel() {
        viewModel.states.observe(this, Observer { render(it) })
        transientEvents.transientEvents.observe(this, Observer { onTransientEventReceived(it) })
    }

    private fun setupViews() {
        fab.setOnClickListener { }
        setSupportActionBar(bottom_bar)
        button.setOnClickListener { emit(MainViewModel.ViewEvent.NewList) }
        collapsing.isTitleEnabled = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> emit(MainViewModel.ViewEvent.NavMenuOpenSelected)
            R.id.add -> askConfirmation()
        }
        return true
    }

    private fun askConfirmation() {
        showConfirmationDialog({ emit(MainViewModel.ViewEvent.NewList) })
    }

    private fun render(viewState: MainViewModel.ViewState) = when (viewState) {
        is MainViewModel.ViewState.Empty -> loading_content_error.error()
        is MainViewModel.ViewState.Content -> loading_content_error.hide()
    }

    private fun onTransientEventReceived(viewState: MainViewModel.TransientEvent) = when (viewState) {
        is MainViewModel.TransientEvent.OpenNavMenu -> openNavMenu(viewState.userChecklists)
        is MainViewModel.TransientEvent.GoToCreateNewList -> startCreateChecklistActivity()
        is MainViewModel.TransientEvent.GoToList -> goToList(viewState.listId)
    }

    private fun goToList(listId: String) {
        supportFragmentManager.beginTransaction().replace(R.id.container, ViewCheckListFragment.fromSelection(listId)).commitAllowingStateLoss()
    }

    private fun startCreateChecklistActivity() {
        startActivityForResult(Intent(this, NewListActivity::class.java), CREATE_NEW_LIST)
        overridePendingTransition(R.anim.slide_up, R.anim.no_change);
    }

    private fun emit(viewEvent: MainViewModel.ViewEvent) {
        viewModel.accept(viewEvent)
    }

    private fun openNavMenu(checkLists: List<TravelCheckListImpl>) {
        BottomNavigationDrawerFragment.newInstance(checkLists).show(supportFragmentManager, BottomNavigationDrawerFragment.TAG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CREATE_NEW_LIST) {
            if (resultCode == RESULT_OK) {
                val checkListId = data?.extras?.getString(CHECKLIST_ID) ?: return
                emit(MainViewModel.ViewEvent.NewListGenerated(checkListId))
            }
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
