package com.pppp.travelchecklist.analytics.customanalytics

import android.content.Context
import com.pppp.travelchecklist.BuildConfig
import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.utils.isDev
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface CustomAnalytics {
    fun logFirstInstall()
}

class IftttAnalytics(
    private val context: Context,
    private val customAnalyticsClient: CustomAnalyticsClient,
    private val preferencesWrapper: PreferencesWrapper
) : CustomAnalytics {

    override fun logFirstInstall() {
        if (BuildConfig.DEBUG) {
            return
        }
        if (!preferencesWrapper.isFirstInstall()) {
            return
        }
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                customAnalyticsClient.firstInstall(context.isDev)
            }
        }
    }
}

