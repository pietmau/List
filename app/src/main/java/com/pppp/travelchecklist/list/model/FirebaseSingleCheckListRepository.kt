package com.pppp.travelchecklist.list.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomCategory
import com.pppp.entities.pokos.RoomCheckListItem
import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.travelchecklist.repository.ListNotFoundException
import com.pppp.travelchecklist.repository.SingleCheckListRepository

class FirebaseSingleCheckListRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : SingleCheckListRepository {

    override fun addNewItemFromTitle(listId: String, categoryId: String, name: String) = TODO()//addItem(listId, categoryId, RoomCheckListItem(title = name))

    override fun addItem(listId: String, categoryId: String, element: RoomCheckListItem) {
        TODO()
//        getUserCheckList(listId) { list ->
//            val updated = list.categories.map {
//                if (it.id == categoryId) {
//                    val mutableList = it.items.toMutableList()
//                    mutableList.add(element)
//                    (it as CategoryImpl).copy(items = mutableList.toList() as List<CheckListItemImpl>)
//                } else it
//            }
//            updateCategoresInternal(listId, updated)
//        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun updateItem(listId: String, categoryId: String, element: RoomCheckListItem) {
        TODO()
//        getUserCheckList(listId) { list ->
//            val updated = list.categories.map { category ->
//                if (category.id == categoryId) {
//                    val updatedCategory = replaceItemInCategory(category, element)
//                    updatedCategory
//                } else {
//                    category
//                }
//            }
//            updateCategoresInternal(listId, updated)
//        }
    }

    private fun replaceItemInCategory(category: Category, element: RoomCheckListItem): RoomCategory {
        val mutableList = category.items
            .toMutableList()
            .map { item -> if (item.id == element.id) element else item }
        return (category as RoomCategory).copy(items = mutableList.toList() as List<RoomCheckListItem>)
    }

    override fun addCategory(listId: String, name: String, callback: (() -> Unit)?) {
        getUserCheckList(listId) { travelCheckslist ->
            val categoryId = travelCheckslist.categories.map { requireNotNull(it.id) }.max()!! + 1
            db.getList(auth.userId, listId)
            TODO()
            //.update("categories", FieldValue.arrayUnion(CategoryImpl(title = name, id = categoryId)))
            //   .addOnSuccessListener { callback?.invoke() }
        }
    }

    override fun updateCategories(listId: String, travelCheckList: TravelCheckList) {
        updateCategoresInternal(listId, travelCheckList.categories)
    }

    private fun updateCategoresInternal(listId: String, categories: List<Category>) {
        db.getList(auth.userId, listId).update("categories", categories)
    }

    override fun getUserCheckListAndUpdates(
        listId: String,
        failure: ((Throwable) -> Unit)?,
        success: ((TravelCheckList) -> Unit)?
    ) {
        db.getList(auth.userId, listId)
            .addSnapshotListener { documentSnapshot, exception ->
                if (exception != null) {
                    failure?.invoke(exception)
                } else {
                    onSuccess(documentSnapshot, success, failure, listId)
                }
            }
    }

    override fun getUserCheckList(
        listId: String,
        failure: ((Throwable) -> Unit)?,
        success: ((TravelCheckList) -> Unit)?
    ) {
        db.getList(auth.userId, listId).get()
            .addOnFailureListener { failure?.invoke(it) }
            .addOnSuccessListener { documentSnapshot ->
                onSuccess(documentSnapshot, success, failure, listId)
            }
    }

    private fun onSuccess(
        documentSnapshot: DocumentSnapshot?,
        success: ((TravelCheckList) -> Unit)?,
        failure: ((Throwable) -> Unit)?,
        listId: String
    ) {
        val checkList = documentSnapshot?.toObject(RoomTravelCheckList::class.java)
        if (checkList != null) {
            success?.invoke(checkList)
        } else {
            failure?.invoke(ListNotFoundException(auth.userId, listId))
        }
    }
}

