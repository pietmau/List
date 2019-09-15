package com.pppp.travelchecklist.list.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.repository.ListNotFoundException
import com.pppp.travelchecklist.repository.SingleCheckListRepository
import com.pppp.travelchecklist.repository.USERS
import com.pppp.travelchecklist.repository.USERS_CHECKLISTS
import com.pppp.travelchecklist.repository.UserNotLoggedInException

class FirebaseSingleCheckListRepository(
    val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

) : SingleCheckListRepository {

    override fun addItem(listId: String, categoryId: Long, name: String) {
        getUserCheckList(listId) { list ->
            val updated = list.categories.map {
                if (it.id == categoryId) {
                    val mutableList = it.items.toMutableList()
                    mutableList.add(CheckListItemImpl(title = name))
                    (it as CategoryImpl).copy(items = mutableList.toList() as List<CheckListItemImpl>)
                } else it
            }
            updateCategoresInternal(listId, updated)
        }
    }

    override fun addCategory(listId: String, name: String) {
        getList(listId).update("categories", FieldValue.arrayUnion(CategoryImpl(title = name)))
    }

    override fun updateCategories(listId: String, travelCheckList: TravelCheckList) {
        updateCategoresInternal(listId, travelCheckList.categories)
    }

    private fun updateCategoresInternal(listId: String, categories: List<Category>) {
        getList(listId).update("categories", categories)
    }

    override fun getUserCheckListAndUpdates(
        listId: String,
        failure: ((Throwable) -> Unit)?,
        success: ((TravelCheckList) -> Unit)?
    ) {
        getList(listId)
            .addSnapshotListener { documentSnapshot, exception ->
                if (exception != null) {
                    failure?.invoke(exception)
                } else {
                    val checkList = documentSnapshot?.toObject(TravelCheckListImpl::class.java)
                    if (checkList != null) {
                        success?.invoke(checkList)
                    } else {
                        onFailure(failure, listId)
                    }
                }
            }
    }

    override fun getUserCheckList(
        listId: String,
        failure: ((Throwable) -> Unit)?,
        success: ((TravelCheckList) -> Unit)?
    ) {
        getList(listId).get()
            .addOnFailureListener { failure?.invoke(it) }
            .addOnSuccessListener { documentSnapshot ->
                val checkList = documentSnapshot?.toObject(TravelCheckListImpl::class.java)
                if (checkList != null) {
                    success?.invoke(checkList)
                } else {
                    onFailure(failure, listId)
                }
            }
    }

    private fun getList(listId: String): DocumentReference {
        return db.collection(USERS)
            .document(getUserId())
            .collection(USERS_CHECKLISTS)
            .document(listId)
    }

    private fun onFailure(failure: ((Throwable) -> Unit)?, listId: String) = failure?.invoke(ListNotFoundException(getUserId(), listId))

    private fun getUserId() = auth.uid ?: throw UserNotLoggedInException()
}