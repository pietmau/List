package com.pppp.travelchecklist.analytics

import android.os.Bundle

interface Logger {
    fun logEvent(tag: String, bundle: Bundle?)

    fun logException(exception: Exception)
}