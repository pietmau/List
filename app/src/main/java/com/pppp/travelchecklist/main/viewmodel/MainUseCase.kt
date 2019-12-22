package com.pppp.travelchecklist.main.viewmodel

interface MainUseCase {

    fun getLastVisitedList(
        success: ((lastListId: Long) -> Unit)? = null,
        failure: (Throwable?) -> Unit = {}
    )

    fun saveLastVisitedList(listId: Long)

    fun deleteCurrentList(complete: (() -> Unit)? = null)

    fun isEmpty(): Boolean
}