package com.pppp.travelchecklist.edititem

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.list.model.getList
import com.pppp.travelchecklist.list.model.userId
import javax.inject.Inject

class FireBaseEditItemModel(
    val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : EditItemModel {

    override fun getItem(listId: String, cardId: String, itemId: String, onFailure: ((Throwable) -> Unit)?, onSuccess: ((CheckListItem) -> Unit)?) {
        db.getList(auth.userId, listId).get().continueWith { task ->
            val checkList = task.result?.toObject(TravelCheckListImpl::class.java)
            val categories = checkList?.categories
            val item = categories?.find { it.id == cardId }?.items?.find { it.id == itemId } ?: throw NullPointerException()
            return@continueWith item
        }
            .addOnSuccessListener { onSuccess?.invoke(it) }
            .addOnFailureListener { onFailure?.invoke(it) }
    }
}