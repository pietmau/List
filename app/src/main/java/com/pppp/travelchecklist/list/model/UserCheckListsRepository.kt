package com.pppp.travelchecklist.list.model

import com.pietrantuono.entities.TravelCheckList

interface UserCheckListsRepository {

    fun getUserCheckLists(): List<TravelCheckList>
}