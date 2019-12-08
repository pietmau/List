package com.pppp.travelchecklist.list.model

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.CheckList
import com.pietrantuono.entities.TravelCheckList

interface SingleCheckListUseCase {

    fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun deleteItem(listId: String, categoryId: String, itemId: String)

    fun moveItem(listId: String, cardId: String, fromPosition: Int, toPosition: Int)

    fun checkItem(listId: String, cardId: String, itemId: String, checked: Boolean)

    fun getUserCheckListAndUxxpdates(listId: Long): LiveData<out CheckList>

    fun ffff(it: CheckList?)
}