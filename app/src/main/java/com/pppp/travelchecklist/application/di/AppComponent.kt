package com.pppp.travelchecklist.application.di

import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.list.di.ViewCheckListComponent
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.login.di.LoginComponent
import com.pppp.travelchecklist.login.di.LoginModule
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.di.MainSubComponent
import com.pppp.travelchecklist.createlist.di.NewListComponent
import com.pppp.travelchecklist.createlist.di.NewListModule
import com.pppp.travelchecklist.utils.UtilsModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, UtilsModule::class))
interface AppComponent {

    fun with(module: MainModule): MainSubComponent

    fun with(module: NewListModule): NewListComponent

    fun with(module: LoginModule): LoginComponent

    fun with(module: ViewCheckListModule): ViewCheckListComponent

    fun inject(app: App)

}