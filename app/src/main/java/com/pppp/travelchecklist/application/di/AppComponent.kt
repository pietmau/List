package com.pppp.travelchecklist.application.di

import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.di.MainSubComponent
import com.pppp.travelchecklist.selector.SelectorComponent
import com.pppp.travelchecklist.selector.SelectorModule
import dagger.Component

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun with(module: MainModule): MainSubComponent

    fun with(module: SelectorModule): SelectorComponent
}