package com.pppp.travelchecklist.notifications.notificationissuer

import android.app.NotificationChannel
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.content.Context
import android.os.Build
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.utils.notificationManager
import javax.inject.Inject

const val CHANNEL_ID = "pppp"
const val SEPARATOR = "/"

class NotificationIssuer @Inject constructor(
    private val notificationMaker: NotificationMaker
) {

    suspend fun issueNotification(context: Context, path: List<String>) {
        if (path.size < 3) {
            return
        }
        createNotificationChannel(context)
        emitNotification(context, path[0], path[1], path[2])
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }
        val name = context.getString(R.string.app_name)
        val channel = NotificationChannel(CHANNEL_ID, name, IMPORTANCE_DEFAULT)
        channel.description = context.getString(R.string.channel_description)
        context.notificationManager?.createNotificationChannel(channel)
    }

    private suspend fun emitNotification(context: Context, listId: String, categoryId: String, itemId: String) {
        val notification = notificationMaker.makeNotification(context, listId, categoryId, itemId) ?: return
        val path = notificationMaker.getPath(listId, categoryId, itemId)
        context.notificationManager?.notify(path.hashCode(), notification)
    }
}