package com.pppp.travelchecklist.repository

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomCheckListItem
import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.travelchecklist.createlist.presenter.Model
import io.reactivex.Single

interface TravelChecklistRepository {

    fun saveAndGet(list: List<Category>, model: Model): Single<Long>

    fun getUserCheckListById(listId: String, success: ((TravelCheckList) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun setName(listId: String, name: String?)

    fun getUsersListsAndUpdates(success: ((List<TravelCheckList>) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun saveLastVisitedList(listId: Long)

    fun getLastVisitedList(success: ((String?) -> Unit)? = null, failure: ((Throwable?) -> Unit)?)

    suspend fun getLastVisitedList(): Long?

    fun deleteChecklist(listId: Long)

    fun getUsersLists(): LiveData<List<RoomTravelCheckList?>>
}

interface SingleCheckListRepository {

    fun getUserCheckListAndUpdates(listId: String, failure: ((Throwable) -> Unit)? = null, success: ((TravelCheckList) -> Unit)? = null)

    fun updateCategories(listId: String, travelCheckList: TravelCheckList)

    fun getUserCheckList(
        listId: String,
        failure: ((Throwable) -> Unit)? = null,
        success: ((TravelCheckList) -> Unit)? = null
    )

    fun addCategory(listId: Long, name: String)

    fun addNewItemFromTitle(listId: Long, categoryId: String, name: String)

    fun addItem(listId: String, categoryId: String, element: RoomCheckListItem)

    fun updateItem(listId: String, categoryId: String, element: RoomCheckListItem)
}