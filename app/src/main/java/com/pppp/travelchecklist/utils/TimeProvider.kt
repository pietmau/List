package com.pppp.travelchecklist.utils

interface TimeProvider {
    fun getCurrentTimeInMills(): Long
}

object TimeProviderImpl : TimeProvider {
    override fun getCurrentTimeInMills(): Long = System.currentTimeMillis()
}