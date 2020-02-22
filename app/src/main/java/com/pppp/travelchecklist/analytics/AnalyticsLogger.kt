package com.pppp.travelchecklist.analytics

import android.os.Bundle

interface AnalyticsLogger : LoginAnalyticsLogger, MainAnalyticsLogger, NotificationsAnalyticsLogger {
    fun logEvent(tag: String, bundle: Bundle?)
    fun logException(exception: Exception)
}

interface LoginAnalyticsLogger {
    fun onAppOnCreate()
    fun onLoginFlowSuccess()
    fun onLoginFlowFailure(reason: Int)
    fun onAppNotEnabled(message: String?)
    fun onLoginFlowStart()
    fun onUserAlreadyLoggedIn()
}

interface MainAnalyticsLogger {
    fun onMainMenuOpen()
    fun goToList(listId: String)
    fun goToCreateNewList()
    fun getLatestListVisited()
}

interface NotificationsAnalyticsLogger {
    fun onBootReceived()
}