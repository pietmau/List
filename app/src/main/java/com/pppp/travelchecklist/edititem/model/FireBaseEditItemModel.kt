package com.pppp.travelchecklist.edititem.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.list.model.FirebaseSingleCheckListRepository
import com.pppp.travelchecklist.list.model.getList
import com.pppp.travelchecklist.list.model.userId

class FireBaseEditItemModel(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val singleCheckListRepository: FirebaseSingleCheckListRepository
) : EditItemModel {

    override fun updateItem(listId: String, categoryId: String, itemId: String, item: CheckListItemImpl) {
        val documentReference = db.getList(auth.userId, listId)
        documentReference.get().continueWith {
            val checkList = requireNotNull(it.result?.toObject(TravelCheckListImpl::class.java))
            val updated = checkList.categories.map { category ->
                if (category.id == categoryId) {
                    val items = category.items.map { if (it.id == item.id) item else it }
                    category.copy(items = items)
                } else category
            }
            documentReference.update("categories", updated)
        }
    }

    override fun retrieveItem(listId: String, cardId: String, itemId: String, onFailure: ((Throwable) -> Unit)?, onSuccess: ((CheckListItem) -> Unit)?) {
        db.getList(auth.userId, listId).get().continueWith { task ->
            return@continueWith getItem(task, cardId, itemId)
        }
            .addOnSuccessListener { onSuccess?.invoke(it) }
            .addOnFailureListener { onFailure?.invoke(it) }
    }

    private fun getItem(
        task: Task<DocumentSnapshot>,
        cardId: String,
        itemId: String
    ): CheckListItemImpl {
        val checkList = task.result?.toObject(TravelCheckListImpl::class.java)
        return getItemFromCategories(checkList, cardId, itemId)
    }

    private fun getItemFromCategories(
        checkList: TravelCheckListImpl?,
        cardId: String,
        itemId: String
    ): CheckListItemImpl {
        val categories = checkList?.categories
        val item = categories?.find { it.id == cardId }?.items?.find { it.id == itemId } ?: throw NullPointerException()
        return item
    }
}