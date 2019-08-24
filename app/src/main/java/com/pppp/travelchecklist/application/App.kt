package com.pppp.travelchecklist.application

import android.app.Application
import android.os.StrictMode
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase
import com.pppp.travelchecklist.BuildConfig
import com.pppp.travelchecklist.application.di.AppComponent
import com.pppp.travelchecklist.application.di.AppModule
import com.pppp.travelchecklist.application.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import io.fabric.sdk.android.Fabric
import com.instabug.library.invocation.InstabugInvocationEvent
import com.instabug.library.Instabug

class App : Application() {
    lateinit var appComponent: AppComponent
    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        LeakCanary.install(this);
        Instabug.Builder(this, BuildConfig.instabug)
            .setInvocationEvents(InstabugInvocationEvent.SHAKE)
            .build()
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    //.detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                         .detectLeakedSqlLiteObjects()
                    //.detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this, firebaseAnalytics)).build()
    }
}