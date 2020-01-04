package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList

interface MainModel {

    fun getUsersLists(success: ((List<TravelCheckList>) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun saveLastVisitedList(listId: String?)

    fun getLastVisitedList(failure: ((Throwable?) -> Unit)? = null, success: ((String?) -> Unit)? = null)

    var checkLists: Map<String, TravelCheckList>

    fun deleteCurrentList()

    fun isEmpty(): Boolean
}
