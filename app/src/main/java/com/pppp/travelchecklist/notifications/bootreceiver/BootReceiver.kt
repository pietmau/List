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
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class BootReceiver : BroadcastReceiver() {
    @Inject
    lateinit var analyticsLogger: NotificationsAnalyticsLogger
    @Inject
    lateinit var alarmSetter: AlarmSetter
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onReceive(context: Context?, intent: Intent?) {
        (context?.applicationContext as App).appComponent.with(NotificationModule).inject(this)
        val pendingResult = goAsync()
        scope.launch {
            analyticsLogger.onBootReceived()
            alarmSetter.setAllAlarms(getAlarmManager(context))
            pendingResult.finish()
        }
    }

    private fun getAlarmManager(context: Context) = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
}