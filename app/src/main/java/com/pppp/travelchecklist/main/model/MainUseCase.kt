package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList

interface MainUseCase {
    fun getUsersListsAndUpdates(success: ((List<TravelCheckList>) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun saveLastVisitedList(it: Long)

    fun getLastVisitedList(success: (id: Long) -> Unit?, failure: ((Throwable?) -> Unit)?)
}

