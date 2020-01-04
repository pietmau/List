package com.pppp.travelchecklist.edititem.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.list.model.getList
import com.pppp.travelchecklist.list.model.userId

internal val CATEGORIES = "categories"

class FireBaseEditItemModel(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val listId: String,
    private val categoryId: String,
    private val itemId: String
) : EditItemModel {

    override fun updateItem(item: CheckListItemImpl) {
        val documentReference = db.getList(auth.userId, listId)
        val task = documentReference.get().continueWith {
            val checkList = requireNotNull(it.result?.toObject(TravelCheckListImpl::class.java))
            val updated = checkList.categories.map { category ->
                if (category.id == categoryId) {
                    val items = category.items.map { if (it.id == item.id) item else it }
                    category.copy(items = items)
                } else category
            }
            documentReference.update(CATEGORIES, updated)
        }
    }

    override fun retrieveItem(onFailure: ((Throwable) -> Unit)?, onSuccess: ((CheckListItem) -> Unit)?) {
        val task: Task<DocumentSnapshot> = db.getList(auth.userId, listId).get()
        val task2: Task<CheckListItemImpl> = task.continueWith { task: Task<DocumentSnapshot> ->
            getItem(task, categoryId, itemId)
        }
        task2.addOnSuccessListener { onSuccess?.invoke(it) }
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