package com.pppp.travelchecklist.main.viewmodel

import com.pppp.entities.pokos.TravelCheckListImpl

interface MainUseCase {
    fun getLastVisitedList(
        failure: (Throwable?) -> Unit = {},
        success: (userLists: List<TravelCheckListImpl>, lastListId: String?) -> Unit = { _, _ -> }
    )

    fun saveLastVisitedList(listId: String)

    fun deleteCurrentList()

    fun isEmpty(): Boolean
}