package com.pppp.travelchecklist.notifications.notificationsetter.intentmaker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.pppp.travelchecklist.notifications.alarmreceiver.AlarmReceiver
import com.pppp.travelchecklist.notifications.notificationsetter.CheckListItemWithIndexes

class IntentMakerImpl(private val context: Context) : IntentMaker {

    override fun makeIntent(checkListItem: CheckListItemWithIndexes) =
        Intent(context, AlarmReceiver::class.java).let {
            PendingIntent.getBroadcast(context, 0, it, 0)
        }
}