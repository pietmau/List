package com.pppp.travelchecklist.analytics.customanalytics

import android.content.Context
import com.pppp.travelchecklist.BuildConfig
import com.pppp.travelchecklist.prefeerences.PreferencesWrapper
import com.pppp.travelchecklist.utils.isDev
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

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

