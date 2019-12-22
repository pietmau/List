package com.pppp.travelchecklist.main.model

import com.pietrantuono.entities.TravelCheckList
import kotlin.properties.Delegates

class MainModelImpl(private val useCase: MainUseCase) : MainModel {

    override var checkLists: Map<String, TravelCheckList> = mutableMapOf()

    private var lastVisitedList: Long by Delegates.notNull()

    override fun saveLastVisitedList(listId: Long?) {
        lastVisitedList = requireNotNull(listId)
        useCase.saveLastVisitedList(lastVisitedList)
    }

    override fun getLastVisitedList(failure: ((Throwable?) -> Unit)?, success: ((listId: Long) -> Unit)) {
        useCase.getLastVisitedList({ listId ->
            listId?.let { success(it) } ?: failure?.invoke(LatestListNotFoundException())
        }, failure)
    }

    override fun deleteCurrentList(success: (() -> Unit)?) {
        useCase.deleteList(lastVisitedList, success)
    }

    override fun isEmpty() = checkLists.isEmpty()

}

class LatestListNotFoundException : Exception()