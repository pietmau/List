package com.pppp.travelchecklist.repository

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomCheckListItem
import com.pppp.travelchecklist.createlist.presenter.Model
import io.reactivex.Single

interface TravelChecklistRepository {

    fun saveAndGet(list: List<Category>, name: Model): Single<Long>

    fun getUserCheckListById(listId: String, success: ((TravelCheckList) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun setName(listId: String, name: String?)

    fun getUsersListsAndUpdates(success: ((List<TravelCheckList>) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun saveLastVisitedList(listId: Long)

    fun getLastVisitedList(success: ((String?) -> Unit)? = null, failure: ((Throwable?) -> Unit)?)

    suspend fun getLastVisitedList(): Long?

    fun deleteChecklist(listId: String)
}

interface SingleCheckListRepository {

    fun getUserCheckListAndUpdates(listId: String, failure: ((Throwable) -> Unit)? = null, success: ((TravelCheckList) -> Unit)? = null)

    fun updateCategories(listId: String, travelCheckList: TravelCheckList)

    fun getUserCheckList(
        listId: String,
        failure: ((Throwable) -> Unit)? = null,
        success: ((TravelCheckList) -> Unit)? = null
    )

    fun addCategory(listId: String, name: String, callback: (() -> Unit)? = null)

    fun addNewItemFromTitle(listId: String, categoryId: String, name: String)

    fun addItem(listId: String, categoryId: String, element: RoomCheckListItem)

    fun updateItem(listId: String, categoryId: String, element: RoomCheckListItem)
}