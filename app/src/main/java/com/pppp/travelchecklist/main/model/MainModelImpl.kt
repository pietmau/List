package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList
import java.lang.NullPointerException

class MainModelImpl(private val useCase: MainUseCase) : MainModel {

    override var checkLists: Map<String, TravelCheckList> = mutableMapOf()

    private var lastVisitedList: Long? = null

    init {
        getUsersLists({
            TODO()
//            checkLists = it.filter { checklist -> checklist.id != null }
//                .map { checklist -> requireNotNull(checklist.id) to checklist }
//                .toMap().toMutableMap()
        }, { /*NoOp*/ })
    }

    override fun getUsersLists(success: ((List<TravelCheckList>) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        useCase.getUsersListsAndUpdates(success, failure)
    }

    override fun saveLastVisitedList(listId: Long?) {
        lastVisitedList = listId
        listId?.let { useCase.saveLastVisitedList(it) }
    }

    override fun getLastVisitedList(success: ((listId: Long) -> Unit), failure: ((Throwable?) -> Unit)?) {
        useCase.getLastVisitedList({ listId ->
            listId?.let { success(it) } ?: failure?.invoke(LastestListNotFoundException())
        }, failure)
    }

    override fun deleteCurrentList() {
        val listId = requireNotNull(lastVisitedList)
        TODO()
//        useCase.deleteChecklist(listId)
//        (checkLists as MutableMap).remove(listId)
//        lastVisitedList = null
    }

    override fun isEmpty() = checkLists.isEmpty()

}

class LastestListNotFoundException : Exception()