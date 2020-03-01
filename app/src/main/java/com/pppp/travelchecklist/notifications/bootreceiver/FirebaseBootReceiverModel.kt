package com.pppp.travelchecklist.notifications.bootreceiver

import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.DateAndTimeProvider
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.UserCheckListsRepository
import javax.inject.Inject

class FirebaseBootReceiverModel @Inject constructor(
    private val repo: UserCheckListsRepository,
    private val timeProvider: DateAndTimeProvider
) : BootReceiverModel {

    @Suppress("UNCHECKED_CAST")
    override suspend fun updateIems() {
        val now = timeProvider.getCurrentTimeInMills()
        repo.getUserCheckLists().forEach { checkList ->
            checkList.categories.forEach { category ->
                category.items.forEach { item ->
                    val alertTime = item.alertTimeInMills ?: Long.MAX_VALUE
                    if (alertTime < now) {
                        updateAndSaveItem(item, requireNotNull(checkList.id), category.id)
                    }
                }
            }
        }
    }

    private suspend fun updateAndSaveItem(item: CheckListItem, checkListId: String, categoryId: String) {
        val item = (item as CheckListItemImpl).copy(isAlertExpired = true)
        repo.updateItem(item, checkListId, categoryId)
    }
}