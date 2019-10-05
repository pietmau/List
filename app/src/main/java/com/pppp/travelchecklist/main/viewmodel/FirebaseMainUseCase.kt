package com.pppp.travelchecklist.main.viewmodel

import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.main.model.MainModel

class FirebaseMainUseCase(private val model: MainModel) : MainUseCase {

    override fun getLastVisitedList(
        success: ((userLists: List<TravelCheckListImpl>, lastListId: String?) -> Unit),
        failure: ((Throwable?) -> Unit)
    ) {
        model.getLastVisitedList(success = { lastList ->
            val userChecklists = model.checkLists as List<TravelCheckListImpl>
            success(userChecklists, lastList)
        }, failure = {
            failure(it)
        })
    }

    override fun saveLastVisitedList(listId: String) {
        model.saveLastVisitedList(listId)
    }
}