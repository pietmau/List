package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.repository.TravelChecklistRepository

class MainModelImpl(private val repo: TravelChecklistRepository) : MainModel {

    override fun getUsersLists(success: ((List<TravelCheckList>) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        repo.getUsersListsAndUpdates(success, failure)
    }

    override fun saveLastVisitedList(listId: String?) {
        listId?.let { repo.saveLastVisitedList(it) }
    }

    override fun getLastVisitedList(success: ((String?) -> Unit)?) = repo.getLastVisitedList(success)
}