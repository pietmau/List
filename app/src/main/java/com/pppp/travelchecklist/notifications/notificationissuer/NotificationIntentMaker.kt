package com.pppp.travelchecklist.notifications.notificationissuer

import android.app.Notification
import android.content.Context

interface NotificationIntentMaker {

    suspend fun makeNotificatinIntent(context: Context, listId: String, categoryId: String?, itemId: String?): Notification
}