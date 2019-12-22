package com.pppp.travelchecklist.main.di

import com.pppp.travelchecklist.application.di.ApplicationScope
import com.pppp.travelchecklist.navigation.BottomNavigationDrawerFragment
import com.pppp.travelchecklist.createlist.NewListActivity
import com.pppp.travelchecklist.main.MainActivity
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainSubComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(newListActivity: NewListActivity)

    fun inject(bottomNavigationDrawerFragment: BottomNavigationDrawerFragment)

}