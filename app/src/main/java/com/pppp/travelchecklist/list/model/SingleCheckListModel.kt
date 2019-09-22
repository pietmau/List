package com.pppp.travelchecklist.list.model

import com.pietrantuono.entities.TravelCheckList

interface SingleCheckListModel {

    fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)?)

    fun deleteItem(listId: String, categoryId: String, itemId: String)

    fun moveItem(listId: String, cardId: String, fromPosition: Int, toPosition: Int)

    fun checkItem(listId: String, cardId: String, itemId: String, checked: Boolean)
}