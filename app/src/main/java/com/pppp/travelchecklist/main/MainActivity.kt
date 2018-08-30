package com.pppp.travelchecklist.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.pppp.travelchecklist.ListFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.findFragmentByTag
import com.pppp.travelchecklist.fragmentTransaction
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.presenter.MainView
import com.pppp.travelchecklist.selector.presenter.SelectionData
import com.pppp.travelchecklist.selector.view.SelectorCallback
import com.pppp.travelchecklist.selector.view.SelectorFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainView {
    override val selectionCallback: SelectorCallback
        get() = (supportFragmentManager.findFragmentById(R.id.container) as SelectorCallback)

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        setUpSelectionFragment(savedInstanceState)
        (applicationContext as App).appComponent?.with(MainModule(this))?.inject(this)
    }

    private fun setUpSelectionFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            fragmentTransaction.replace(R.id.container, getSelectorFragment(), SelectorFragment.TAG).commit()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unbind()
    }

    override fun navigateToNewList(selection: SelectionData) {
        fragmentTransaction.replace(R.id.container, ListFragment.fromSelection(selection), ListFragment.TAG).commit()
    }

    private fun getSelectorFragment() =
        findFragmentByTag<ListFragment>(SelectorFragment.TAG) ?: SelectorFragment.newInstance()

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
