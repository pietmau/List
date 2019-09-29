package com.pppp.travelchecklist.application

import android.app.Application
import android.os.StrictMode
import android.provider.Settings
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase
import com.pppp.travelchecklist.BuildConfig
import com.pppp.travelchecklist.application.di.AppComponent
import com.pppp.travelchecklist.application.di.AppModule
import com.pppp.travelchecklist.application.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import com.instabug.library.invocation.InstabugInvocationEvent
import com.instabug.library.Instabug
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import javax.inject.Inject

class App : Application() {
    lateinit var appComponent: AppComponent
    lateinit var firebaseAnalytics: FirebaseAnalytics
    @Inject
    lateinit var analytics: AnalyticsLogger

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        firebaseAnalytics = setUpAnalytics()
        LeakCanary.install(this);
        Instabug.Builder(this, BuildConfig.instabug)
            .setInvocationEvents(InstabugInvocationEvent.NONE)
            .build()
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
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
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this, firebaseAnalytics)).build().also { it.inject(this) }
        analytics.onAppOnCreate()
    }

    private fun setUpAnalytics() =
        FirebaseAnalytics.getInstance(this).apply {
            setAnalyticsCollectionEnabled(!BuildConfig.DEBUG)
            if (Settings.Secure.getInt(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) == 1) {
                setUserProperty("is dev", "is dev")
            }
        }

}