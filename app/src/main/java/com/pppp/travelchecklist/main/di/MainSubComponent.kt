package com.pppp.travelchecklist.main.di

import com.pppp.travelchecklist.main.BottomNavigationDrawerFragment
import com.pppp.travelchecklist.main.CreateChecklistActivity
import com.pppp.travelchecklist.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainSubComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(createChecklistActivity: CreateChecklistActivity)

    fun inject(bottomNavigationDrawerFragment: BottomNavigationDrawerFragment)

}