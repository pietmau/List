package com.pppp.travelchecklist.notifications.notificationissuer

import android.app.Notification
import android.content.Context
import android.net.Uri

interface NotificationMaker {

    suspend fun makeNotification(context: Context, listId: String, categoryId: String, itemId: String): Notification?

    fun getPath(listId: String, categoryId: String, itemId: String): Uri
}