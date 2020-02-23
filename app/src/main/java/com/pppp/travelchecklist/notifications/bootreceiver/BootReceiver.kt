package com.pppp.travelchecklist.notifications.bootreceiver

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pppp.travelchecklist.analytics.NotificationsAnalyticsLogger
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.notifications.di.NotificationModule
import com.pppp.travelchecklist.notifications.alarmsetter.AlarmSetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val BOOT_COMPLTETED = "android.intent.action.BOOT_COMPLETED"

class BootReceiver : BroadcastReceiver() {
    @Inject
    lateinit var analyticsLogger: NotificationsAnalyticsLogger
    @Inject
    lateinit var alarmSetter: AlarmSetter

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action?.equals(BOOT_COMPLTETED) == false) {
            return
        }
        (context?.applicationContext as App).appComponent.with(NotificationModule).inject(this)
        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            analyticsLogger.onBootReceived()
            val alarmManager = getAlarmManager(context)
            //alarmSetter.setAlarm(alarmManager)
            alarmSetter.setAllAlarms(alarmManager)
            pendingResult.finish()
        }
    }

    private fun getAlarmManager(context: Context) = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
}