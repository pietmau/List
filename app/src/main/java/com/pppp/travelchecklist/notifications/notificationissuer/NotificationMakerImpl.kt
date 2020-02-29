package com.pppp.travelchecklist.notifications.notificationissuer

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.net.Uri
import androidx.core.app.NotificationCompat
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.notifications.alarmsetter.intentmaker.IntentMaker
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.UserCheckListsRepository
import javax.inject.Inject

class NotificationMakerImpl @Inject constructor(
    private val repo: UserCheckListsRepository,
    private val intentMaker: IntentMaker
) : NotificationMaker {

    suspend override fun makeNotification(
        context: Context,
        listId: String,
        categoryId: String,
        itemId: String
    ): Notification? {
        val list = repo.getListById(listId)
        val checkListItem = getItem(list, categoryId, itemId) ?: return null
        val pendingIntent: PendingIntent = makePendingIntentForNotification(listId, categoryId, itemId)
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_check_box_24px_notifcation)
            .setContentTitle(list.name)
            .setContentText(checkListItem.title)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()
    }

    override fun getPath(listId: String, categoryId: String, itemId: String): Uri = intentMaker.makeUri(listId, categoryId, itemId)

    private fun makePendingIntentForNotification(listId: String, categoryId: String, itemId: String) =
        intentMaker.makePendingIntentForNotification(listId, categoryId, itemId)

    private fun getItem(
        list: TravelCheckList,
        categoryId: String?,
        itemId: String?
    ) = list.categories.find { it.id == categoryId }?.items?.find { it.id == itemId }
}