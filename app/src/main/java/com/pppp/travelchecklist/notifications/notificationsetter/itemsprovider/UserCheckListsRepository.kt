package com.pppp.travelchecklist.notifications.notificationsetter.itemsprovider

import com.pietrantuono.entities.TravelCheckList

interface UserCheckListsRepository {

    fun getUserCheckLists(): List<TravelCheckList>
}