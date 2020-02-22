package com.pppp.travelchecklist.notifications.notificationsetter

interface TimeProvider {
    fun getCurrentTimeInMills(): Long
}