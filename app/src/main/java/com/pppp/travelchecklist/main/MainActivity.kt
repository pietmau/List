package com.pppp.travelchecklist.main

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.login.Consumer
import com.pppp.travelchecklist.login.Producer
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.presenter.ErrorCallback
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ErrorCallback, BottomNavigationDrawerFragment.BottomNavigationItemListener {
    @Inject
    lateinit var producer: Producer<MainPresenter.ViewState>

    @Inject
    lateinit var consumer: Consumer<MainPresenter.ViewEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        is MainPresenter.ViewState.GoToCreateNewList -> startCreateChecklistActivity()
    }

    private fun startCreateChecklistActivity() {
        startActivityForResult(Intent(this, CreateChecklistActivity::class.java), CreateChecklistActivity.REQUEST_CODE)
        overridePendingTransition(R.anim.slide_up, R.anim.no_change);
    }

    private fun emit(viewEvent: MainPresenter.ViewEvent) {
        consumer.push(viewEvent)
    }

    private fun openNavMenu(checkLists: List<TravelCheckListImpl>) {
        BottomNavigationDrawerFragment.newInstance(checkLists).show(supportFragmentManager, BottomNavigationDrawerFragment.TAG)
    }

    override fun onNavItemSelected(id: Int, title: String) {
        emit(MainPresenter.ViewEvent.NavItemSelected(id, title))
    }

    override fun onError(text: String) {
        Snackbar.make(root, text, Snackbar.LENGTH_LONG).show()
    }
}
