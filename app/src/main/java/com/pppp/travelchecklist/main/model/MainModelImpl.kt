package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import java.lang.NullPointerException

class MainModelImpl(private val repo: MainUseCase) : MainModel {

    override var checkLists: Map<String, TravelCheckList> = mutableMapOf()

    private var lastVisitedList: Long? = null

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

    override fun saveLastVisitedList(listId: Long?) {
        lastVisitedList = listId
        listId?.let { repo.saveLastVisitedList(it) }
    }

    override fun getLastVisitedList(success: ((listId: Long) -> Unit)?, failure: ((Throwable?) -> Unit)?) {
        failure?.invoke(NullPointerException(("TODO")))
//        if (lastVisitedList == null) {
//            repo.getLastVisitedList({
//                lastVisitedList = it
//                success?.invoke(it)
//            }, failure)
//        } else {
//            success?.invoke(lastVisitedList)
//        }
    }

    override fun deleteCurrentList() {
        val listId = requireNotNull(lastVisitedList)
        TODO()
//        repo.deleteChecklist(listId)
//        (checkLists as MutableMap).remove(listId)
//        lastVisitedList = null
    }

    override fun isEmpty() = checkLists.isEmpty()

}