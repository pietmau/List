package com.pppp.travelchecklist.analytics

import android.os.Bundle
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.pppp.travelchecklist.analytics.customanalytics.CustomAnalytics

private val LOGIN_FLOW_FAILURE: String = "login_flow_failure"
private val APP_DISABLED: String = "app_disabled"
private val LOGIN_FAILURE_REASON: String = "login_failure_reason"
private val LOGIN_FLOW_START: String = "login_flow_start"
private val LOGIN_FLOW_SUCCESS: String = "login_flow_success"
private val USER_IS_ALREDY_LOGGED_IN: String = "user_already_loggedin"

class FirebaseAnalyticsLogger(
    private val firebaseAnalytics: FirebaseAnalytics,
    private val customAnalytics: CustomAnalytics
) : AnalyticsLogger {

    override fun onMainMenuOpen() {
    }

    override fun goToList(listId: String) {
    }

    override fun goToCreateNewList() {
    }

    override fun getLatestListVisited() {
    }

    override fun logEvent(tag: String, bundle: Bundle?) = firebaseAnalytics.logEvent(tag, bundle)

    override fun logException(exception: Exception) = Crashlytics.logException(exception)

    override fun onLoginFlowSuccess() = firebaseAnalytics.logEvent(LOGIN_FLOW_SUCCESS, null)

    override fun onLoginFlowFailure(reason: Int) = firebaseAnalytics.logEvent(LOGIN_FLOW_FAILURE, Bundle().apply { putInt(LOGIN_FAILURE_REASON, reason) })

    override fun onAppNotEnabled(message: String?) {
        firebaseAnalytics.logEvent(APP_DISABLED, Bundle().apply {
            putString("MESSAGE", message)
        })
    }

    override fun onLoginFlowStart() = firebaseAnalytics.logEvent(LOGIN_FLOW_START, null)

    override fun onUserAlreadyLoggedIn() = firebaseAnalytics.logEvent(USER_IS_ALREDY_LOGGED_IN, null)

    override fun onAppOnCreate() = customAnalytics.logFirstInstall()
}

