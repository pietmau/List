package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList

interface MainModel {

    fun getUsersLists(success: ((List<TravelCheckList>) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun saveLastVisitedList(listId: Long?)

    fun getLastVisitedList(success: ((Long) -> Unit), failure: ((Throwable?) -> Unit)? = null)

    var checkLists: Map<String, TravelCheckList>

    fun deleteCurrentList()

    fun isEmpty(): Boolean
}
