package com.pppp.travelchecklist.main

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.list.view.ViewCheckListFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.findFragmentByTag
import com.pppp.travelchecklist.fragmentTransaction
import com.pppp.travelchecklist.login.Consumer
import com.pppp.travelchecklist.login.Producer
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.presenter.MainView
import com.pppp.travelchecklist.newlist.view.NewListCallback
import com.pppp.travelchecklist.newlist.view.NewListFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {
    override val selectionCallback: NewListCallback?
        get() = (supportFragmentManager.findFragmentById(R.id.container) as? NewListCallback)
    @Inject
    lateinit var producer: Producer<MainPresenter.ViewState>

    @Inject
    lateinit var consumer: Consumer<MainPresenter.ViewEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpSelectionFragment(savedInstanceState)
        (applicationContext as App).appComponent.with(MainModule(this)).inject(this)
        bottom_bar.setNavigationOnClickListener { view ->
            emit(MainPresenter.ViewEvent.NavMenuOpenSelected)
        }
        producer.states.observe(this, Observer {
            render(it)
        })
    }

    private fun render(viewState: MainPresenter.ViewState) = when (viewState) {
        is MainPresenter.ViewState.OpenNavMenu -> openNavMenu(viewState.userChecklists)
    }

    private fun emit(viewEvent: MainPresenter.ViewEvent) {
        consumer.push(viewEvent)
    }

    private fun openNavMenu(checkLists: List<TravelCheckListImpl>) {
        BottomNavigationDrawerFragment.newInstance(checkLists)
            .show(supportFragmentManager, BottomNavigationDrawerFragment.TAG)
    }

    private fun setUpSelectionFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            fragmentTransaction.replace(R.id.container, getSelectorFragment(), NewListFragment.TAG).commit()
        }
    }

    override fun navigateToNewList(checkListId: String) {
        supportFragmentManager.findFragmentById(R.id.container)?.let { fragmentTransaction.remove(it) }
        fragmentTransaction.replace(R.id.container, ViewCheckListFragment.fromSelection(checkListId), ViewCheckListFragment.TAG).commit()
    }

    private fun getSelectorFragment() =
        findFragmentByTag<ViewCheckListFragment>(NewListFragment.TAG) ?: NewListFragment.newInstance()

    override fun onError(text: String) {
        Snackbar.make(root, text, Snackbar.LENGTH_LONG).show()
    }
}
