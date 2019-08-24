package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.repository.TravelChecklistRepository

class MainModelImpl(private val repo: TravelChecklistRepository) : MainModel {
    override val isEmpty: Boolean
        get() = checkLists.isEmpty()

    override var checkLists: List<TravelCheckList> = emptyList()

    init {
        getUsersLists({
            checkLists = it
        }, {})
    }

    override operator fun get(index: Int) = checkLists[index]

    override operator fun contains(position: Int) = (position in 0..(checkLists.size - 1))

    override fun getUsersLists(success: ((List<TravelCheckList>) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        repo.getUsersListsAndUpdates(success, failure)
    }

    override fun saveLastVisitedList(listId: String?) {
        listId?.let { repo.saveLastVisitedList(it) }
    }

    override fun getLastVisitedList(success: ((String?) -> Unit)?, failure: ((Throwable?) -> Unit)?) = repo.getLastVisitedList(success, failure)
}