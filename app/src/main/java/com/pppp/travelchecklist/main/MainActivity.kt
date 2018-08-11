package com.pppp.travelchecklist.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.pppp.travelchecklist.NewListFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.presenter.MainView
import com.pppp.travelchecklist.selector.view.SelectorFragment
import com.pppp.travelchecklist.selector.view.model.Selection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainView {
    @Inject lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        setUpSelectionFragment(savedInstanceState)
        (applicationContext as App).appComponent?.with(MainModule(this))?.inject(this)
    }

    private fun setUpSelectionFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            var fragment = getSelectorFragment() ?: SelectorFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, SelectorFragment.TAG).commit()
        }
        getSelectorFragment()?.setCallback(presenter)
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unbind()
    }

    override fun navigateToNewList(selection: Selection) {
//        var fragment = getNewlistFragment() ?: NewListFragment.newInstance(selection)
//        supportFragmentManager.beginTransaction().replace(R.id.container, fragment, NewListFragment.TAG).commit()
    }

    private fun getNewlistFragment() =
        supportFragmentManager.findFragmentByTag(NewListFragment.TAG) as? NewListFragment

    private fun getSelectorFragment() =
        supportFragmentManager.findFragmentByTag(SelectorFragment.TAG) as? SelectorFragment

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
