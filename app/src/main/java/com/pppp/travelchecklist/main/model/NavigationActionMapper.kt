package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.main.BottomNavigationDrawerFragment
import com.pppp.travelchecklist.main.viewmodel.MainViewModel

class NavigationActionMapperImpl : NavigationActionMapper {
    override fun map(navigationAction: BottomNavigationDrawerFragment.NavigationAction): MainViewModel.ViewEvent {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

interface NavigationActionMapper : Mapper<BottomNavigationDrawerFragment.NavigationAction, MainViewModel.ViewEvent> {

    override fun map(navigationAction: BottomNavigationDrawerFragment.NavigationAction): MainViewModel.ViewEvent

}

interface Mapper<IN, OUT> {

    fun map(navigationAction: IN): OUT
}


