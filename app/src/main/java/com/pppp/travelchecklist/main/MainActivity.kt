package com.pppp.travelchecklist.main

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.list.view.ViewCheckListFragment
import com.pppp.travelchecklist.login.Consumer
import com.pppp.travelchecklist.login.Producer
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.presenter.ErrorCallback
import com.pppp.travelchecklist.main.presenter.TransientEvents
import com.pppp.travelchecklist.newlist.NewListActivity
import com.pppp.travelchecklist.newlist.NewListActivity.Companion.CHECKLIST_ID
import com.pppp.travelchecklist.newlist.NewListActivity.Companion.CREATE_NEW_LIST
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ErrorCallback, BottomNavigationDrawerFragment.BottomNavigationItemListener {
    @Inject
    lateinit var producer: Producer<MainPresenter.ViewState>
    @Inject
    lateinit var consumer: Consumer<MainPresenter.ViewEvent>
    @Inject
    lateinit var transientEvents: TransientEvents<MainPresenter.TransientEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as App).appComponent.with(MainModule(this)).inject(this)
        bottom_bar.setNavigationOnClickListener { emit(MainPresenter.ViewEvent.NavMenuOpenSelected) }
        producer.states.observe(this, Observer { render(it) })
        transientEvents.subscribe { onTransientEventReceived(it) }
    }

    private fun render(viewState: MainPresenter.ViewState): Nothing = TODO()

    private fun onTransientEventReceived(viewState: MainPresenter.TransientEvent) = when (viewState) {
        is MainPresenter.TransientEvent.OpenNavMenu -> openNavMenu(viewState.userChecklists)
        is MainPresenter.TransientEvent.GoToCreateNewList -> startCreateChecklistActivity()
        is MainPresenter.TransientEvent.GoToList -> goToList(viewState.listId)
    }

    private fun goToList(listId: String) {
        supportFragmentManager.beginTransaction().replace(R.id.container, ViewCheckListFragment.fromSelection(listId)).commit()
    }

    private fun startCreateChecklistActivity() {
        startActivityForResult(Intent(this, NewListActivity::class.java), CREATE_NEW_LIST)
        overridePendingTransition(R.anim.slide_up, R.anim.no_change);
    }

    private fun emit(viewEvent: MainPresenter.ViewEvent) {
        consumer.push(viewEvent)
    }

    private fun openNavMenu(checkLists: List<TravelCheckListImpl>) {
        BottomNavigationDrawerFragment.newInstance(checkLists).show(supportFragmentManager, BottomNavigationDrawerFragment.TAG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CREATE_NEW_LIST) {
            if (resultCode == RESULT_OK) {
                val checkListId = data?.extras?.getString(CHECKLIST_ID) ?: return
                emit(MainPresenter.ViewEvent.NewListGenerated(checkListId))
            }
        }
    }

    override fun onNavItemSelected(id: Int, title: String) {
        emit(MainPresenter.ViewEvent.NavItemSelected(id, title))
    }

    override fun onError(text: String) {
        Snackbar.make(root, text, Snackbar.LENGTH_LONG).show()
    }
}
