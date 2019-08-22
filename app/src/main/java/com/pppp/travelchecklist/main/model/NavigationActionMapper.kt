package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.main.BottomNavigationDrawerFragment
import com.pppp.travelchecklist.main.viewmodel.MainViewModel

object NavigationActionMapperImpl : NavigationActionMapper {

    override fun map(navigationAction: BottomNavigationDrawerFragment.NavigationAction): MainViewModel.ViewEvent =
        when (navigationAction) {
            is BottomNavigationDrawerFragment.NavigationAction.NewList -> MainViewModel.ViewEvent.NewList
            is BottomNavigationDrawerFragment.NavigationAction.NavigateToExistingList -> MainViewModel.ViewEvent.NavItemSelected(navigationAction.id)
        }
}

interface NavigationActionMapper : Mapper<BottomNavigationDrawerFragment.NavigationAction, MainViewModel.ViewEvent> {

    override fun map(navigationAction: BottomNavigationDrawerFragment.NavigationAction): MainViewModel.ViewEvent

}

interface Mapper<IN, OUT> {

    fun map(navigationAction: IN): OUT
}


