package com.pppp.travelchecklist.repository

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import io.reactivex.Single

interface TravelChecklistRepository {

    fun saveAndGet(list: List<Category>, name: String): Single<String>

    fun getUserCheckListById(listId: String, success: ((TravelCheckList) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun setName(listId: String, name: String?)

    fun getUsersListsAndUpdates(success: ((List<TravelCheckList>) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)
}

interface SingleCheckListRepository {

    fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun updateList(listId: String, travelCheckList: TravelCheckList)

}