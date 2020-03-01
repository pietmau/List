package com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider

import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CheckListItemImpl

interface UserCheckListsRepository {

    suspend fun getUserCheckLists(): List<TravelCheckList>

    suspend fun getListById(id: String): TravelCheckList

    suspend fun updateItem(item: CheckListItemImpl, listId: String, categoryId: Any?)
}