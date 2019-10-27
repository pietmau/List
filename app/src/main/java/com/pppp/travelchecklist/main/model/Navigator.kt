package com.pppp.travelchecklist.main.model

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.view.ViewCheckListFragment
import com.pppp.travelchecklist.navigation.BottomNavigationDrawerFragment
import com.pppp.travelchecklist.main.viewmodel.MainViewAction
import com.pppp.travelchecklist.newlist.NewListActivity

object NavigatorImpl : Navigator {

    override fun removeListFragment(activity: AppCompatActivity) {
        activity.supportFragmentManager.apply {
            findFragmentByTag(ViewCheckListFragment.TAG)?.let {
                this.beginTransaction().remove(it).commitAllowingStateLoss()
            }
        }
    }

    override fun goToList(activity: AppCompatActivity, listId: String) {
        activity.supportFragmentManager.beginTransaction().replace(R.id.container, ViewCheckListFragment.fromSelection(listId), ViewCheckListFragment.TAG)
            .commitAllowingStateLoss()
    }

    override fun startCreateChecklistActivity(activity: Activity) {
        activity.startActivityForResult(Intent(activity, NewListActivity::class.java), NewListActivity.CREATE_NEW_LIST)
        activity.overridePendingTransition(R.anim.slide_up, R.anim.no_change);
    }

    override fun map(navigationAction: BottomNavigationDrawerFragment.NavigationAction): MainViewAction =
        when (navigationAction) {
            is BottomNavigationDrawerFragment.NavigationAction.NewList -> MainViewAction.GoMakeNewList
            is BottomNavigationDrawerFragment.NavigationAction.NavigateToExistingList -> MainViewAction.NavItemSelected(navigationAction.id)
        }
}

interface Navigator : Mapper<BottomNavigationDrawerFragment.NavigationAction, MainViewAction> {

    override fun map(navigationAction: BottomNavigationDrawerFragment.NavigationAction): MainViewAction

    fun startCreateChecklistActivity(activity: Activity)

    fun goToList(activity: AppCompatActivity, listId: String)

    fun removeListFragment(activity: AppCompatActivity)

}

interface Mapper<IN, OUT> {

    fun map(navigationAction: IN): OUT
}


