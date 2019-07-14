package com.pppp.travelchecklist.application.di

import com.pppp.travelchecklist.list.di.ViewCheckListComponent
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.login.LoginComponent
import com.pppp.travelchecklist.login.LoginModule
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.di.MainSubComponent
import com.pppp.travelchecklist.selector.SelectorComponent
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.utils.UtilsModule
import dagger.Component

@Component(modules = arrayOf(AppModule::class, UtilsModule::class))
interface AppComponent {

    fun with(module: MainModule): MainSubComponent

    fun with(module: SelectorModule): SelectorComponent

    fun with(module: LoginModule): LoginComponent

    fun with(module: ViewCheckListModule): ViewCheckListComponent

}