package com.pppp.travelchecklist.notifications.alarmsetter.intentmaker

import android.app.PendingIntent
import android.net.Uri

interface IntentMaker {
    fun makeAlarmIntent(checkListItem: String, catagoryId: String, itemId: String): PendingIntent

    fun makePendingIntentForNotification(listId: String, categoryId: String, itemId: String): PendingIntent

    fun makeUri(listId: String, catagoryId: String, itemId: String): Uri
}
