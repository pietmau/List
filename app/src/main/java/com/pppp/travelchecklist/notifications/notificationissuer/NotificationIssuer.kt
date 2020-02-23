package com.pppp.travelchecklist.notifications.notificationissuer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.content.Context
import android.os.Build
import com.pppp.travelchecklist.R
import javax.inject.Inject

const val CHANNEL_ID = "pppp"

class NotificationIssuer @Inject constructor(
    private val notificationIntentMaker: NotificationIntentMaker
) {

    suspend fun issueNotification(context: Context, path: List<String>) {
        if (path.size < 3) {
            return
        }
        createNotificationChannel(context)
        emitNotification(context, path)
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }
        val name = context.getString(R.string.app_name)
        val channel = NotificationChannel(CHANNEL_ID, name, IMPORTANCE_DEFAULT)
        channel.description = context.getString(R.string.channel_description)
        getNotificationManager(context).createNotificationChannel(channel)
    }

    private suspend fun emitNotification(context: Context, path: List<String>) {
        val listId = path[0]
        val categoryId = path[1]
        val itemId = path[2]
        val notification = notificationIntentMaker.makeNotificatinIntent(context, listId, categoryId, itemId)
        getNotificationManager(context).notify(path.hashCode(), notification)
    }

    private fun getNotificationManager(context: Context) = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
}