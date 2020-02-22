package com.pppp.travelchecklist.notifications.notificationsetter.intentmaker

import android.app.PendingIntent
import com.pppp.travelchecklist.notifications.notificationsetter.CheckListItemWithIndexes

interface IntentMaker {
    fun makeIntent(checkListItem: CheckListItemWithIndexes): PendingIntent
}
