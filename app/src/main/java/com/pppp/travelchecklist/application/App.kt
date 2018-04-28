package com.pppp.travelchecklist.application

import android.app.Application
import android.os.StrictMode
import com.pppp.travelchecklist.BuildConfig
import com.pppp.travelchecklist.application.di.AppComponent
import com.pppp.travelchecklist.application.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary


class App : Application() {
    var appComponent: AppComponent? = null

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build())
            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build())
        }
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        appComponent = DaggerAppComponent.create()
    }
}