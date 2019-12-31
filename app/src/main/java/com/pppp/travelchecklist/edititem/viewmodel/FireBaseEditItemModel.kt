package com.pppp.travelchecklist.edititem.viewmodel

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.list.model.getList
import com.pppp.travelchecklist.list.model.userId
import com.pppp.travelchecklist.repository.SingleCheckListRepository

class FireBaseEditItemModel(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val singleCheckListRepository: SingleCheckListRepository
) : EditItemModel {

    override fun retrieveItemAndUpdates(
        listId: String,
        cardId: String,
        itemId: String,
        onFailure: ((Throwable) -> Unit)?,
        onSuccess: ((CheckListItem) -> Unit)?
    ) {
        db.getList(auth.userId, listId)
            .addSnapshotListener { snapShot, exception ->
                if (exception == null) {
                    val checkList = snapShot?.toObject(TravelCheckListImpl::class.java)
                    val item = getgItemFromCategories(checkList, cardId, itemId)
                    onSuccess?.invoke(item)
                } else {
                    onFailure?.invoke(exception)
                }
            }
    }

    override fun retrieveItem(listId: String, cardId: String, itemId: String, onFailure: ((Throwable) -> Unit)?, onSuccess: ((CheckListItem) -> Unit)?) {
        db.getList(auth.userId, listId).get().continueWith { task ->
            return@continueWith getItem(task, cardId, itemId)
        }
            .addOnSuccessListener { onSuccess?.invoke(it) }
            .addOnFailureListener { onFailure?.invoke(it) }
    }

    override fun updateItem(
        title: String,
        description: String,
        priority: Int,
        itemId: String,
        listId: String,
        cardId: String,
        alertTimeInMills: Long?
    ) {
        db.getList(auth.userId, listId).get().continueWith { task ->
            val item = getItem(task, cardId, itemId)
            val copy = item.copy(title = title, description = description, priority = priority, alertTimeInMills = alertTimeInMills)
            singleCheckListRepository.updateItem(listId, cardId, copy)
        }
    }

    private fun getItem(
        task: Task<DocumentSnapshot>,
        cardId: String,
        itemId: String
    ): CheckListItemImpl {
        val checkList = task.result?.toObject(TravelCheckListImpl::class.java)
        return getgItemFromCategories(checkList, cardId, itemId)
    }

    private fun getgItemFromCategories(
        checkList: TravelCheckListImpl?,
        cardId: String,
        itemId: String
    ): CheckListItemImpl {
        val categories = checkList?.categories
        val item = categories?.find { it.id == cardId }?.items?.find { it.id == itemId } ?: throw NullPointerException()
        return item
    }
}