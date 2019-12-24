package com.pppp.travelchecklist.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.createlist.presenter.Model
import io.reactivex.Single

interface TravelChecklistRepository {

    fun saveAndGet(list: List<Category>, name: Model): Single<String>

    fun getUserCheckListById(listId: String, success: ((TravelCheckList) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun setName(listId: String, name: String?)

    fun getUsersListsAndUpdates(success: ((List<TravelCheckList>) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null)

    fun saveLastVisitedList(listId: String)

    fun getLastVisitedList(success: ((String?) -> Unit)? = null, failure: ((Throwable?) -> Unit)?)

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

    fun addItem(listId: String, categoryId: String, element: CheckListItemImpl)

    fun updateItem(listId: String, categoryId: String, element: CheckListItemImpl)

    fun getChecklistById(listId: String): DocumentReference

    fun getListById(listId: String, doStuffWithTheList: (list: TravelCheckListImpl, docReference: DocumentReference) -> Unit): Task<Unit>
}