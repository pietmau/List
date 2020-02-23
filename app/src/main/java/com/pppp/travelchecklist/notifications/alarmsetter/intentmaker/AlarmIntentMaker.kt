package com.pppp.travelchecklist.notifications.alarmsetter.intentmaker

import android.app.PendingIntent
import com.pppp.travelchecklist.notifications.alarmsetter.CheckListItemWithIndexes

interface AlarmIntentMaker {
    fun makeAlarmIntent(checkListItem: CheckListItemWithIndexes): PendingIntent

    fun makeEmptyAlarmIntent(): PendingIntent
}
