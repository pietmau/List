package com.pppp.travelchecklist.main.viewmodel

import com.pppp.entities.pokos.TravelCheckListImpl

interface MainUseCase {
    fun getLastVisitedList(
        success: (userLists: List<TravelCheckListImpl>, lastListId: String?) -> Unit = { _, _ -> },
        failure: (Throwable?) -> Unit = {}
    )

    fun saveLastVisitedList(listId: String)
}