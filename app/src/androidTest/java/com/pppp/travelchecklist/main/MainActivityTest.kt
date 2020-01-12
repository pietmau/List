package com.pppp.travelchecklist.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.firebase.analytics.FirebaseAnalytics
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.application.di.AppModule
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {

        override fun afterActivityLaunched() {
            super.afterActivityLaunched()
            val app = activity.application as App
            app.appComponent =
                DaggerMockAppComponent.builder().mockAppModule(MockAppModule(app)).build().also { it.inject(app) }
        }
    }

    @Test
    fun name() {

    }
}