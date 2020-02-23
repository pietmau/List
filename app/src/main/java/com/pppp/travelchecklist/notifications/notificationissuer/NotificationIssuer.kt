package com.pppp.travelchecklist.notifications.notificationissuer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.UserCheckListsRepository
import javax.inject.Inject

private const val CHANNEL_ID = "pppp"

class NotificationIssuer @Inject constructor(private val repo: UserCheckListsRepository) {

    fun issueNotification(context: Context, path: List<String>) {
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

    private fun emitNotification(context: Context, path: List<String>) {
        var builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_check_box_24px_notifcation)
            .setContentTitle("Foo")
            .setContentText("textContent")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        getNotificationManager(context).notify(path.hashCode(), builder.build())
    }

    private fun getNotificationManager(context: Context) = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
}