package com.pppp.travelchecklist.notifications.alarmsetter.intentmaker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.pppp.travelchecklist.notifications.alarmreceiver.AlarmReceiver
import com.pppp.travelchecklist.notifications.alarmsetter.CheckListItemWithIndexes

// TODO fix all of this
class IntentMakerImpl(private val context: Context) : IntentMaker {

    override fun makeIntent(checkListItem: CheckListItemWithIndexes): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.data = Uri.parse("pppp://" + "/" + checkListItem.listId + "/" + checkListItem.categoryId + "/" + checkListItem.checkListItem.id)
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }
}