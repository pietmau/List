package com.pppp.travelchecklist.main.viewmodel

import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.travelchecklist.main.model.MainModel

class FirebaseMainUseCase(private val model: MainModel) : MainUseCase {

    override fun deleteCurrentList() = model.deleteCurrentList()

    @Suppress("UNCHECKED_CAST")
    override fun getLastVisitedList(
        success: ((lastListId: Long) -> Unit),
        failure: ((Throwable?) -> Unit)
    ) {
        model.getLastVisitedList(success = { lastList ->
            success(lastList)
        }, failure = {
            failure(it)
        })
    }

    override fun saveLastVisitedList(listId: Long) {
        model.saveLastVisitedList(listId)
    }

    override fun isEmpty() = model.isEmpty()
}