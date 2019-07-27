package com.pppp.travelchecklist.main

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.pppp.travelchecklist.list.view.ViewCheckListFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.findFragmentByTag
import com.pppp.travelchecklist.fragmentTransaction
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.presenter.MainView
import com.pppp.travelchecklist.newlist.view.NewListCallback
import com.pppp.travelchecklist.newlist.view.NewListFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainView {
    override val selectionCallback: NewListCallback?
        get() = (supportFragmentManager.findFragmentById(R.id.container) as? NewListCallback)

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
        (applicationContext as App).appComponent.with(MainModule(this)).inject(this)
    }

    private fun setUpSelectionFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            fragmentTransaction.replace(R.id.container, getSelectorFragment(), NewListFragment.TAG).commit()
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

    override fun navigateToNewList(checkListId: String) {
        supportFragmentManager.findFragmentById(R.id.container)?.let { fragmentTransaction.remove(it) }
        fragmentTransaction.replace(R.id.container, ViewCheckListFragment.fromSelection(checkListId), ViewCheckListFragment.TAG).commit()
    }

    private fun getSelectorFragment() =
        findFragmentByTag<ViewCheckListFragment>(NewListFragment.TAG) ?: NewListFragment.newInstance()

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

    override fun onError(text: String) {
        Snackbar.make(root, text, Snackbar.LENGTH_LONG).show()
    }
}
