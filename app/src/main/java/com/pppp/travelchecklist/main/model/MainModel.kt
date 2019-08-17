package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList

interface MainModel {

    fun getUsersLists(success: ((List<TravelCheckList>) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun saveLastVisitedList(listId: String?)

    fun getLastVisitedList(success: ((String?) -> Unit)? = null)
    var checkLists: List<TravelCheckList>
    operator fun get(index: Int): TravelCheckList
    operator fun contains(position: Int): Boolean
}
