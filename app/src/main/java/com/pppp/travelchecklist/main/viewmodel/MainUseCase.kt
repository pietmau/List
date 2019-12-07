package com.pppp.travelchecklist.main.viewmodel

import com.pppp.entities.pokos.TravelCheckListImpl

interface MainUseCase {

    fun getLastVisitedList(
        success: (userLists: List<TravelCheckListImpl>, lastListId: Long) -> Unit = { _, _ -> },
        failure: (Throwable?) -> Unit = {}
    )

    fun saveLastVisitedList(listId: Long)

    fun deleteCurrentList()

    fun isEmpty(): Boolean
}