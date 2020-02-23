package com.pppp.travelchecklist.notifications.alarmsetter.intentmaker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.pppp.travelchecklist.notifications.alarmreceiver.AlarmReceiver
import com.pppp.travelchecklist.notifications.alarmsetter.CheckListItemWithIndexes

const val LIST_ID = "list_id"
const val CATEGORY_ID = "category_id"
const val ITEM_ID = "category_id"

class AlarmIntentMakerImpl(private val context: Context) : AlarmIntentMaker {

    override fun makeAlarmIntent(checkListItem: CheckListItemWithIndexes): PendingIntent {
        val intent = makeIntent()
        intent.putExtra(LIST_ID, checkListItem.listId)
        intent.putExtra(CATEGORY_ID, checkListItem.categoryId)
        intent.putExtra(ITEM_ID, checkListItem.checkListItem.id)
        return makePendingIntent(intent)
    }

    override fun makeEmptyAlarmIntent() = makePendingIntent(makeIntent())

    private fun makeIntent() = Intent(context, AlarmReceiver::class.java)

    private fun makePendingIntent(intent: Intent) = PendingIntent.getBroadcast(context, 0, intent, 0)
}