package com.pppp.travelchecklist.application

import android.app.Application
import com.pppp.travelchecklist.application.di.AppComponent
import com.pppp.travelchecklist.application.di.DaggerAppComponent


class App : Application() {
    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}