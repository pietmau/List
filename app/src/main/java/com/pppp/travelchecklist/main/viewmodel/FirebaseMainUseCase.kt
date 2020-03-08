package com.pppp.travelchecklist.main.viewmodel

import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.main.model.MainModel

class FirebaseMainUseCase(
    private val model: MainModel,
    private val settingsUseCase: SettingsUseCase
) : MainUseCase, SettingsUseCase by settingsUseCase {

    override suspend fun deleteCurrentList() = model.deleteCurrentList()

    @Suppress("UNCHECKED_CAST")
    override fun getLastVisitedList(
        failure: (Throwable?) -> Unit,
        success: (userLists: List<TravelCheckListImpl>, lastListId: String?) -> Unit
    ) {
        model.getLastVisitedList(failure = {
            failure(it)
        }, success = { lastList ->
            val userChecklists = model.checkLists.values.toList()
            success(userChecklists as List<TravelCheckListImpl>, lastList)
        })
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun getLastVisitedListId(path: List<String>) =
        if (path.size == 3) {
            path[0]
        } else model.getLastVisitedListId()

    override fun saveLastVisitedList(listId: String) {
        model.saveLastVisitedList(listId)
    }

    override fun isEmpty() = model.isEmpty()
}