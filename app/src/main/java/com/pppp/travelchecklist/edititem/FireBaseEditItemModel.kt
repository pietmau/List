package com.pppp.travelchecklist.edititem

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.RoomCheckListItem
import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.travelchecklist.list.model.getList
import com.pppp.travelchecklist.list.model.userId
import com.pppp.travelchecklist.repository.SingleCheckListRepository

class FireBaseEditItemModel(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val singleCheckListRepository: SingleCheckListRepository
) : EditItemModel {

    private lateinit var cardId: String
    private lateinit var listId: String
    override lateinit var item: CheckListItem

    override fun retrieveItem(listId: String, cardId: String, itemId: String, onFailure: ((Throwable) -> Unit)?, onSuccess: ((CheckListItem) -> Unit)?) {
        this.listId = listId
        this.cardId = cardId
        db.getList(auth.userId, listId).get().continueWith { task ->
            val checkList = task.result?.toObject(RoomTravelCheckList::class.java)
            val categories = checkList?.categories
            //val item = categories?.find { it.id == cardId }?.items?.find { it.id == itemId } ?: throw NullPointerException()
            TODO()
            this.item = item
            return@continueWith item
        }
            .addOnSuccessListener { onSuccess?.invoke(it) }
            .addOnFailureListener { onFailure?.invoke(it) }
    }

    override fun updateItem(title: String, description: String, priority: Int) {
        val copy = (item as RoomCheckListItem).copy(title = title, description = description, priority = priority)
        singleCheckListRepository.updateItem(listId, cardId, copy)
    }
}