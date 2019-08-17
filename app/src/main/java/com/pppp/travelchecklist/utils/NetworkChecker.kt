package com.pppp.travelchecklist.utils

interface NetworkChecker {
    fun isNetworkAvailable(): Boolean

    fun checkNetworkConnectionRepeatedly(success: (() -> Unit)? = null, failure: ((ErrorMessage) -> Unit)? = null)

    fun cancelNetworkChecks()

    data class ErrorMessage(val message: String)
}
