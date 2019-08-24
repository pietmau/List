package com.pppp.travelchecklist.main.model

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.view.ViewCheckListFragment
import com.pppp.travelchecklist.main.BottomNavigationDrawerFragment
import com.pppp.travelchecklist.main.viewmodel.MainViewModel
import com.pppp.travelchecklist.newlist.NewListActivity

object NavigatorImpl : Navigator {

    override fun goToList(activity: AppCompatActivity, listId: String) {
        activity.supportFragmentManager.beginTransaction().replace(R.id.container, ViewCheckListFragment.fromSelection(listId)).commitAllowingStateLoss()
    }

    override fun startCreateChecklistActivity(activity: Activity) {
        activity.startActivityForResult(Intent(activity, NewListActivity::class.java), NewListActivity.CREATE_NEW_LIST)
        activity.overridePendingTransition(R.anim.slide_up, R.anim.no_change);
    }

    override fun map(navigationAction: BottomNavigationDrawerFragment.NavigationAction): MainViewModel.ViewEvent =
        when (navigationAction) {
            is BottomNavigationDrawerFragment.NavigationAction.NewList -> MainViewModel.ViewEvent.NewList
            is BottomNavigationDrawerFragment.NavigationAction.NavigateToExistingList -> MainViewModel.ViewEvent.NavItemSelected(navigationAction.id)
        }
}

interface Navigator : Mapper<BottomNavigationDrawerFragment.NavigationAction, MainViewModel.ViewEvent> {

    override fun map(navigationAction: BottomNavigationDrawerFragment.NavigationAction): MainViewModel.ViewEvent

    fun startCreateChecklistActivity(activity: Activity)

    fun goToList(activity: AppCompatActivity, listId: String)

}

interface Mapper<IN, OUT> {

    fun map(navigationAction: IN): OUT
}


