package com.pppp.travelchecklist.repository

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import io.reactivex.Single

interface TravelChecklistRepository {

    fun saveAndGet(list: List<Category>): Single<String>

    fun getUserCheckList(listId: String, success: ((TravelCheckList) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)
}