package com.pppp.travelchecklist.application

import android.app.Application
import android.os.StrictMode
import com.google.firebase.database.FirebaseDatabase
import com.pppp.travelchecklist.BuildConfig
import com.pppp.travelchecklist.application.di.AppComponent
import com.pppp.travelchecklist.application.di.AppModule
import com.pppp.travelchecklist.application.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary


class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectActivityLeaks()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}