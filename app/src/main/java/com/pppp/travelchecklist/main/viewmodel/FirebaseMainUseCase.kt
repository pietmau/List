package com.pppp.travelchecklist.main.viewmodel

import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.main.model.MainModel

class FirebaseMainUseCase(private val model: MainModel) : MainUseCase {

    override fun deleteCurrentList() = model.deleteCurrentList()

    @Suppress("UNCHECKED_CAST")
    override fun  getLastVisitedList(
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

    override fun saveLastVisitedList(listId: String) {
        model.saveLastVisitedList(listId)
    }

    override fun isEmpty() = model.isEmpty()
}