package com.pppp.travelchecklist.main.model

import android.view.MenuItem
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.main.BottomNavigationDrawerFragment
import com.pppp.travelchecklist.main.viewmodel.MainViewModel

object NavigatorImpl : Navigator {

    override fun map(navigationAction: BottomNavigationDrawerFragment.NavigationAction): MainViewModel.ViewEvent =
        when (navigationAction) {
            is BottomNavigationDrawerFragment.NavigationAction.NewList -> MainViewModel.ViewEvent.NewList
            is BottomNavigationDrawerFragment.NavigationAction.NavigateToExistingList -> MainViewModel.ViewEvent.NavItemSelected(navigationAction.id)
        }
}

interface Navigator : Mapper<BottomNavigationDrawerFragment.NavigationAction, MainViewModel.ViewEvent> {

    override fun map(navigationAction: BottomNavigationDrawerFragment.NavigationAction): MainViewModel.ViewEvent

}

interface Mapper<IN, OUT> {

    fun map(navigationAction: IN): OUT
}


