package com.pppp.travelchecklist.notifications.alarmreceiver

import android.content.Context
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.UserCheckListsRepository
import com.pppp.travelchecklist.notifications.notificationissuer.NotificationIssuer
import javax.inject.Inject

class AlarmReceiverPresenter @Inject constructor(
    var notificationIssuer: NotificationIssuer
) {
    suspend fun onReceive(context: Context, path: List<String>) {
        if (path.size < 3) {
            return
        }
        val listId = path[0]
        val categoryId = path[1]
        val itemId = path[2]
        notificationIssuer.issueNotification(context, listId, categoryId, itemId)
    }
}