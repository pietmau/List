package com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider

import com.pietrantuono.entities.TravelCheckList

interface UserCheckListsRepository {

    suspend fun getUserCheckLists(): List<TravelCheckList>

    suspend fun getListById(id: String): TravelCheckList
}