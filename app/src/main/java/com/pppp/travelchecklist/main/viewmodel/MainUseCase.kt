package com.pppp.travelchecklist.main.viewmodel

import com.pppp.entities.pokos.TravelCheckListImpl

interface MainUseCase : SettingsUseCase {
    fun getLastVisitedList(
        failure: (Throwable?) -> Unit = {},
        success: (userLists: List<TravelCheckListImpl>, lastListId: String?) -> Unit = { _, _ -> }
    )

    fun saveLastVisitedList(listId: String)

    fun deleteCurrentList()

    fun isEmpty(): Boolean

    suspend fun getLastVisitedListId(path: List<String>): String?
}