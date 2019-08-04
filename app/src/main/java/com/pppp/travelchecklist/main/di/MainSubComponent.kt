package com.pppp.travelchecklist.main.di

import com.pppp.travelchecklist.main.BottomNavigationDrawerFragment
import com.pppp.travelchecklist.newlist.NewListActivity
import com.pppp.travelchecklist.main.MainActivity
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainSubComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(newListActivity: NewListActivity)

    fun inject(bottomNavigationDrawerFragment: BottomNavigationDrawerFragment)

}