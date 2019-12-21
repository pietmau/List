package com.pppp.travelchecklist.list.model

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomTravelCheckList

interface SingleCheckListUseCase {

    fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun deleteItem(listId: String, categoryId: String, itemId: String)

    fun moveItem(listId: String, cardId: String, fromPosition: Int, toPosition: Int)

    fun checkItem(listId: Long, cardId: String, itemId: Long, checked: Boolean)

    fun getUserCheckListAndUpdates(listId: Long): LiveData<RoomTravelCheckList?>
}