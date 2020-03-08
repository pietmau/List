package com.pppp.travelchecklist.main.di

import androidx.fragment.app.FragmentActivity
import com.pppp.travelchecklist.navigation.BottomNavigationDrawerFragment
import com.pppp.travelchecklist.createlist.NewListActivity
import com.pppp.travelchecklist.main.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [MainModule::class])
interface MainSubComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(newListActivity: NewListActivity)

    fun inject(bottomNavigationDrawerFragment: BottomNavigationDrawerFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: FragmentActivity): MainSubComponent
    }
}