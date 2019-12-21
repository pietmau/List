package com.pppp.travelchecklist.main.viewmodel

import com.pppp.entities.pokos.RoomTravelCheckList

interface MainUseCase {

    fun getLastVisitedList(
        success: (lastListId: Long) -> Unit = { _ -> },
        failure: (Throwable?) -> Unit = {}
    )

    fun saveLastVisitedList(listId: Long)

    fun deleteCurrentList()

    fun isEmpty(): Boolean
}