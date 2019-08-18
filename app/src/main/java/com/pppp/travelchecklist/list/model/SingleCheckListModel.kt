package com.pppp.travelchecklist.list.model

import com.pietrantuono.entities.TravelCheckList

interface SingleCheckListModel {

    fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)?)

    fun deleteItem(listId: String, categoryId: Long, itemId: Long)

    fun moveItems(cardId: Long, fromPosition: Int, toPosition: Int)
}