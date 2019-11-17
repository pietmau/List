package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.repository.TravelChecklistRepository

class MainModelImpl(private val repo: TravelChecklistRepository) : MainModel {

    override var checkLists: Map<String, TravelCheckList> = mutableMapOf()

    private var lastVisitedList: String? = null

    init {
        getUsersLists({
            checkLists = it.filter { checklist -> checklist.id != null }
                .map { checklist -> requireNotNull(checklist.id) to checklist }
                .toMap().toMutableMap()
        }, { /*NoOp*/ })
    }

    override fun getUsersLists(success: ((List<TravelCheckList>) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        repo.getUsersListsAndUpdates(success, failure)
    }

    override fun saveLastVisitedList(listId: String?) {
        lastVisitedList = listId
        listId?.let { repo.saveLastVisitedList(it) }
    }

    override fun getLastVisitedList(success: ((String?) -> Unit)?, failure: ((Throwable?) -> Unit)?) {
        if (lastVisitedList == null) {
            repo.getLastVisitedList({
                lastVisitedList = it
                success?.invoke(it)
            }, failure)
        } else {
            success?.invoke(lastVisitedList)
        }
    }

    override fun deleteCurrentList() {
        val listId = requireNotNull(lastVisitedList)
        repo.deleteChecklist(listId)
        (checkLists as MutableMap).remove(listId)
        lastVisitedList = null
    }

    override fun isEmpty() = checkLists.isEmpty()

}