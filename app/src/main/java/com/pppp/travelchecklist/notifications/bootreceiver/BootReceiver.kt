package com.pppp.travelchecklist.notifications.bootreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pppp.travelchecklist.analytics.NotificationsAnalyticsLogger
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.notifications.di.NotificationModule
import com.pppp.travelchecklist.notifications.notificationsetter.setter.NotificationSetter
import javax.inject.Inject

private val BOOT_COMPLTETED = "android.intent.action.BOOT_COMPLETED"

class BootReceiver : BroadcastReceiver() {
    @Inject
    lateinit var analyticsLogger: NotificationsAnalyticsLogger
    @Inject
    lateinit var notificationSetter: NotificationSetter

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action?.equals(BOOT_COMPLTETED) == false) {
            return
        }
        (context as App).appComponent.with(NotificationModule).inject(this)
        analyticsLogger.onBootReceived()

    }
}