package com.pppp.travelchecklist.notifications.alarmreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.notifications.di.NotificationModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlarmReceiver : BroadcastReceiver() {
    @Inject
    lateinit var presenter: AlarmReceiverPresenter

    override fun onReceive(context: Context?, intent: Intent?) {
        (context?.applicationContext as App).appComponent.with(NotificationModule).inject(this)
        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            val path = intent?.data?.pathSegments ?: return@launch
            presenter.onReceive(context, path)
            pendingResult.finish()
        }
    }
}