package com.pppp.travelchecklist.list.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.repository.ListNotFoundException
import com.pppp.travelchecklist.repository.SingleCheckListRepository

class FirebaseSingleCheckListRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : SingleCheckListRepository {

    override fun getListById(listId: String, doStuffWithTheList: (list: TravelCheckListImpl, docReference: DocumentReference) -> Unit): Task<Unit> {
        val list = db.getList(auth.userId, listId)
        return list.get().continueWith {
            val checkList = requireNotNull(it.result?.toObject(TravelCheckListImpl::class.java))
            doStuffWithTheList(checkList, list)
        }
    }

    override fun getChecklistById(listId: String) = db.getList(auth.userId, listId)

    override fun addNewItemFromTitle(listId: String, categoryId: String, name: String) = addItem(listId, categoryId, CheckListItemImpl(title = name))

    override fun addItem(listId: String, categoryId: String, element: CheckListItemImpl) {
        getUserCheckList(listId) { list ->
            val updated = list.categories.map {
                if (it.id == categoryId) {
                    val mutableList = it.items.toMutableList()
                    mutableList.add(element)
                    (it as CategoryImpl).copy(items = mutableList.toList() as List<CheckListItemImpl>)
                } else it
            }
            updateCategoresInternal(listId, updated)
        }
    }

    override fun updateItem(listId: String, categoryId: String, element: CheckListItemImpl) {
        getListById(listId) { checkList, documentReference ->
            val updated = checkList.categories.map { category ->
                if (category.id == categoryId) replaceItemInCategory(category, element) else category
            }
            documentReference.update("categories", updated)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun replaceItemInCategory(category: Category, element: CheckListItemImpl): CategoryImpl {
        val mutableList = category.items
            .toMutableList()
            .map { item -> if (item.id == element.id) element else item }
        return (category as CategoryImpl).copy(items = mutableList.toList() as List<CheckListItemImpl>)
    }

    override fun addCategory(listId: String, name: String, callback: (() -> Unit)?) {
        getListById(listId) { checkList, documentReference ->
            val categoryId = checkList.categories.map { it.id }.max() + 1
            documentReference.update("categories", FieldValue.arrayUnion(CategoryImpl(title = name, id = categoryId)))
        }.addOnSuccessListener { callback?.invoke() }
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
        val checkList = documentSnapshot?.toObject(TravelCheckListImpl::class.java)
        if (checkList != null) {
            success?.invoke(checkList)
        } else {
            failure?.invoke(ListNotFoundException(auth.userId, listId))
        }
    }
}

