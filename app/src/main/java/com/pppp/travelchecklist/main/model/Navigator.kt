package com.pppp.travelchecklist.main.model

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.view.CheckListFragment
import com.pppp.travelchecklist.navigation.BottomNavigationDrawerFragment
import com.pppp.travelchecklist.main.MainViewIntent
import com.pppp.travelchecklist.createlist.NewListActivity
import com.pppp.travelchecklist.settings.SettingsActivity

object NavigatorImpl : Navigator {

    override fun goToSettings(activity: Activity) {
        activity.startActivity(Intent(activity, SettingsActivity::class.java))
    }

    override fun removeListFragment(activity: AppCompatActivity) {
        activity.supportFragmentManager.apply {
            findFragmentByTag(CheckListFragment.TAG)?.let {
                this.beginTransaction().remove(it).commitAllowingStateLoss()
            }
        }
    }

    override fun goToList(activity: AppCompatActivity, listId: String) {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, CheckListFragment.fromSelection(listId), CheckListFragment.TAG)
            .commit()
    }

    override fun startCreateChecklistActivity(activity: Activity) {
        activity.startActivityForResult(Intent(activity, NewListActivity::class.java), NewListActivity.CREATE_NEW_LIST)
        activity.overridePendingTransition(R.anim.slide_up, R.anim.no_change);
    }

    override fun map(navigationAction: BottomNavigationDrawerFragment.NavigationAction): MainViewIntent =
        when (navigationAction) {
            is BottomNavigationDrawerFragment.NavigationAction.NewList -> MainViewIntent.GoMakeNewList
            is BottomNavigationDrawerFragment.NavigationAction.NavigateToExistingList -> MainViewIntent.NavItemSelected(navigationAction.id)
        }
}

interface Navigator : Mapper<BottomNavigationDrawerFragment.NavigationAction, MainViewIntent> {

    override fun map(navigationAction: BottomNavigationDrawerFragment.NavigationAction): MainViewIntent

    fun startCreateChecklistActivity(activity: Activity)

    fun goToList(activity: AppCompatActivity, listId: String)

    fun removeListFragment(activity: AppCompatActivity)

    fun goToSettings(activity: Activity)
}

interface Mapper<IN, OUT> {

    fun map(navigationAction: IN): OUT
}


