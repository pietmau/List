package com.pppp.travelchecklist.analytics

import android.os.Bundle
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseLogger(
    private val firebaseAnalytics: FirebaseAnalytics
) : Logger {

    override fun logEvent(tag: String, bundle: Bundle?) = firebaseAnalytics.logEvent(tag, bundle)

    override fun logException(exception: Exception) = Crashlytics.logException(exception)

}