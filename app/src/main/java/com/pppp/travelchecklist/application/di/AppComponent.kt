package com.pppp.travelchecklist.application.di

import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.di.MainSubComponent
import dagger.Component

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun with(module: MainModule): MainSubComponent
}