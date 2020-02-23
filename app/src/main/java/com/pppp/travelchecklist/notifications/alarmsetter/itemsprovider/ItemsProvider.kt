package com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider

import com.pppp.travelchecklist.notifications.alarmsetter.CheckListItemWithIndexes

interface ItemsProvider {
    suspend fun getUserItemsWithAlarm(): List<CheckListItemWithIndexes>
}
