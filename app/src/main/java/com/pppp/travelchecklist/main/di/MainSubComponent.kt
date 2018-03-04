package com.pppp.travelchecklist.main.di

import com.pppp.travelchecklist.main.TestFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainSubComponent {

    fun inject(testFragment: TestFragment)
}