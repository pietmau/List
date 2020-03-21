package com.pppp.travelchecklist.notifications.alarmsetter.intentmaker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.pppp.travelchecklist.main.MainActivity
import com.pppp.travelchecklist.notifications.alarmreceiver.AlarmReceiver
import com.pppp.travelchecklist.notifications.notificationissuer.SEPARATOR
import javax.inject.Inject

const val SCHEMA = "pppp://"
const val AUTHORITY = "alarms"

class IntentMakerImpl @Inject constructor(private val context: Context) : IntentMaker {

    override fun makeAlarmIntent(listId: String, catagoryId: String, itemId: String): PendingIntent {
        val intent = makeAlarmIntent()
        intent.data = makeUri(listId, catagoryId, itemId)
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    override fun makePendingIntentForNotification(listId: String, categoryId: String, itemId: String): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)
        intent.data = makeUri(listId, categoryId, itemId)
        return PendingIntent.getActivity(context, 0, intent, 0)
    }

    override fun makeUri(listId: String, catagoryId: String, itemId: String) =
        Uri.parse("$SCHEMA$AUTHORITY$SEPARATOR$listId$SEPARATOR$catagoryId$SEPARATOR$itemId")

    private fun makeAlarmIntent() = Intent(context, AlarmReceiver::class.java)

}