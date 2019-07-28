package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.repository.TravelChecklistRepository

class MainModelImpl(private val repo: TravelChecklistRepository) : MainModel {

    override fun getUsersLists(success: ((List<TravelCheckList>) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        repo.getUsersLists(success, failure)
    }
}