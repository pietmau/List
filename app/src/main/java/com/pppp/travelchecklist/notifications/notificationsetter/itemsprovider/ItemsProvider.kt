package com.pppp.travelchecklist.notifications.notificationsetter.itemsprovider

import com.pppp.travelchecklist.notifications.notificationsetter.CheckListItemWithIndexes

interface ItemsProvider {
    fun getUserItemsWithAlarm(): List<CheckListItemWithIndexes>
}
