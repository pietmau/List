package com.pppp.travelchecklist.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.list.view.ViewCheckListFragment
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.viewmodel.MainViewModel
import com.pppp.travelchecklist.main.viewmodel.ErrorCallback
import com.pppp.travelchecklist.TransientEvents
import com.pppp.travelchecklist.newlist.NewListActivity
import com.pppp.travelchecklist.newlist.NewListActivity.Companion.CHECKLIST_ID
import com.pppp.travelchecklist.newlist.NewListActivity.Companion.CREATE_NEW_LIST
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ErrorCallback, BottomNavigationDrawerFragment.BottomNavigationItemListener {
    @Inject
    lateinit var producer: Producer<MainViewModel.ViewState>
    @Inject
    lateinit var consumer: Consumer<MainViewModel.ViewEvent>
    @Inject
    lateinit var transientEvents: TransientEvents<MainViewModel.TransientEvent>

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
        producer.states.observe(this, Observer { render(it) })
        transientEvents.transientEvents.observe(this, Observer {
            onTransientEventReceived(it)
        })
    }

    private fun setupViews() {
        fab.setOnClickListener { emit(MainViewModel.ViewEvent.NavMenuOpenSelected) }
        setSupportActionBar(bottom_bar)
        button.setOnClickListener {
            emit(MainViewModel.ViewEvent.OnButtonClicked)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    private fun render(viewState: MainViewModel.ViewState): Nothing = TODO()

    private fun onTransientEventReceived(viewState: MainViewModel.TransientEvent) = when (viewState) {
        is MainViewModel.TransientEvent.OpenNavMenu -> openNavMenu(viewState.userChecklists)
        is MainViewModel.TransientEvent.GoToCreateNewList -> startCreateChecklistActivity()
        is MainViewModel.TransientEvent.GoToList -> goToList(viewState.listId)
        is MainViewModel.TransientEvent.Empty -> loading_content_error.error()
    }

    private fun goToList(listId: String) {
        loading_content_error.hide()
        replaceFragment(ViewCheckListFragment.fromSelection(listId))
    }

    private fun replaceFragment(fromSelection: ViewCheckListFragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fromSelection).commitAllowingStateLoss()
    }

    private fun startCreateChecklistActivity() {
        startActivityForResult(Intent(this, NewListActivity::class.java), CREATE_NEW_LIST)
        overridePendingTransition(R.anim.slide_up, R.anim.no_change);
    }

    private fun emit(viewEvent: MainViewModel.ViewEvent) {
        consumer.push(viewEvent)
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

    override fun onNavItemSelected(id: Int, title: String) {
        emit(MainViewModel.ViewEvent.NavItemSelected(id, title))
    }

    override fun onError(text: String) {
        Snackbar.make(root, text, Snackbar.LENGTH_LONG).show()
    }
}
