package com.pppp.travelchecklist.notifications.notificationsetter

import com.pietrantuono.entities.CheckListItem

data class CheckListItemWithIndexes(val listId: String, val categoryId: String, val checkListItem: CheckListItem)