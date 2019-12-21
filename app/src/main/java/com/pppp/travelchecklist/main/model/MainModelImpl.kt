package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList

class MainModelImpl(private val useCase: MainUseCase) : MainModel {

    override var checkLists: Map<String, TravelCheckList> = mutableMapOf()

    private var lastVisitedList: Long? = null

    override fun saveLastVisitedList(listId: Long?) {
        lastVisitedList = listId
        listId?.let { useCase.saveLastVisitedList(it) }
    }

    override fun getLastVisitedList(failure: ((Throwable?) -> Unit)?, success: ((listId: Long) -> Unit)) {
        useCase.getLastVisitedList({ listId ->
            listId?.let { success(it) } ?: failure?.invoke(LatestListNotFoundException())
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

class LatestListNotFoundException : Exception()