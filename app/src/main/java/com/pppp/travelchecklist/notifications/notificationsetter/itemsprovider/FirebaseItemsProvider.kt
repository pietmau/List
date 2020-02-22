package com.pppp.travelchecklist.notifications.notificationsetter.itemsprovider

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.notifications.notificationsetter.CheckListItemWithIndexes
import com.pppp.travelchecklist.utils.TimeProvider
import com.pppp.travelchecklist.utils.TimeProviderImpl

class FirebaseItemsProvider(
    private val userCheckListsRepository: UserCheckListsRepository = FirebaseUserCheckListsRepository(),
    private val timeProvider: TimeProvider = TimeProviderImpl
) :
    ItemsProvider {

    override fun getUserItemsWithAlarm(): List<CheckListItemWithIndexes> {
        val now = timeProvider.getCurrentTimeInMills()
        return userCheckListsRepository.getUserCheckLists()
            .flatMap { travelCheckList ->
                travelCheckList.categories
                    .flatMap { category ->
                        category.getItemsWithAlarm()
                            .filterNot { it.isInThePast(now) }
                            .map { CheckListItemWithIndexes(requireNotNull(travelCheckList.id), category.id, it) }
                    }
            }
    }
}

private fun Category.getItemsWithAlarm() = items.filter { it.isAlertOn }.filter { it.alertTimeInMills != null }

private fun CheckListItem.isInThePast(now: Long) = alertTimeInMills ?: 0 < now
