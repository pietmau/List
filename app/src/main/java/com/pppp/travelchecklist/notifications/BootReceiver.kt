package com.pppp.travelchecklist.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.notifications.di.NotificationModule

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        (context as App).appComponent.with(NotificationModule).inject(this)
    }
}