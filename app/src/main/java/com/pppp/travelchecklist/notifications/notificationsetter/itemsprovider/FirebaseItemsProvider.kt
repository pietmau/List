package com.pppp.travelchecklist.notifications.notificationsetter.itemsprovider

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.list.model.UserCheckListsRepository
import com.pppp.travelchecklist.notifications.notificationsetter.CheckListItemWithIndexes
import com.pppp.travelchecklist.notifications.notificationsetter.TimeProvider

class FirebaseItemsProvider(private val userCheckListsRepository: UserCheckListsRepository, private val timeProvider: TimeProvider) :
    ItemsProvider {

    override fun getUserItemsWithAlarm(): List<CheckListItemWithIndexes> {
        val now = timeProvider.getCurrentTimeInMills()
        return userCheckListsRepository.getUserCheckLists()
            .flatMap { travelCheckList ->
                travelCheckList.categories
                    .flatMap { category ->
                        category.getItemsWithAlarm()
                            .filterNot { it.isInThePast(now) }
                            .map { CheckListItemWithIndexes(travelCheckList.id, category.id, it) }
                    }
            }
    }
}

private fun Category.getItemsWithAlarm() = items.filter { it.isAlertOn }.filter { it.alertTimeInMills != null }

private fun CheckListItem.isInThePast(now: Long) = alertTimeInMills ?: 0 < now

