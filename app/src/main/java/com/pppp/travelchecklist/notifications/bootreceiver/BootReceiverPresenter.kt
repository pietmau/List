package com.pppp.travelchecklist.notifications.bootreceiver

import android.app.AlarmManager
import com.pppp.travelchecklist.analytics.NotificationsAnalyticsLogger
import com.pppp.travelchecklist.notifications.alarmsetter.AlarmSetter
import javax.inject.Inject

class BootReceiverPresenter @Inject constructor(
    val analyticsLogger: NotificationsAnalyticsLogger,
    val alarmSetter: AlarmSetter,
    val bootReceiverModel: BootReceiverModel
) {

    suspend fun onReceive(alarmManager: AlarmManager) {
        analyticsLogger.onBootReceived()
        alarmSetter.setAllAlarms(alarmManager)
        bootReceiverModel.updateIems()
    }
}