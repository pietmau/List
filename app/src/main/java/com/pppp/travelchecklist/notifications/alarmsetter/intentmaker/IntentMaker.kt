package com.pppp.travelchecklist.notifications.alarmsetter.intentmaker

import android.app.PendingIntent
import com.pppp.travelchecklist.notifications.alarmsetter.CheckListItemWithIndexes

interface IntentMaker {
    fun makeIntent(checkListItem: CheckListItemWithIndexes): PendingIntent
}
