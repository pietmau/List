package com.pppp.travelchecklist.notifications.notificationissuer

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.UserCheckListsRepository
import javax.inject.Inject

class NotificationIntentMakerImpl @Inject constructor(private val repo: UserCheckListsRepository) : NotificationIntentMaker {

    suspend override fun makeNotificatinIntent(
        context: Context,
        listId: String,
        categoryId: String?,
        itemId: String?
    ): Notification {
        val list = repo.getListById(listId)
        val checkListItem = requireNotNull(list.categories.find { it.id == categoryId }?.items?.find { it.id == itemId })
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_check_box_24px_notifcation)
            .setContentTitle(list.name)
            .setContentText(checkListItem.title)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()
    }
}