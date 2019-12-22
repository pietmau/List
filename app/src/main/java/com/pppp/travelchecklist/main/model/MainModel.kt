package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList

interface MainModel {

    fun saveLastVisitedList(listId: Long?)

    fun getLastVisitedList(failure: ((Throwable?) -> Unit)? = null, success: ((Long) -> Unit))

    var checkLists: Map<String, TravelCheckList>

    fun deleteCurrentList(success: (() -> Unit)? = null)

    fun isEmpty(): Boolean
}
