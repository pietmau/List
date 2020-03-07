package com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.edititem.model.CATEGORIES
import com.pppp.travelchecklist.list.model.getList
import com.pppp.travelchecklist.list.model.getUserLists
import com.pppp.travelchecklist.list.model.userId
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebaseUserCheckListsRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : UserCheckListsRepository {

    override suspend fun getUserCheckLists(): List<TravelCheckList> =
        suspendCancellableCoroutine { continuation ->
            db.getUserLists(auth.userId).get().addOnSuccessListener { query ->
                val value = query.documents.map { document ->
                    val travelCheckList = document.toObject(TravelCheckListImpl::class.java)
                    travelCheckList?.copy(id = document.id)
                }.filterNotNull()
                continuation.resume(value)
            }.addOnFailureListener {
                continuation.resumeWithException(it)
            }
        }

    override suspend fun getListById(id: String): TravelCheckList =
        suspendCancellableCoroutine { continuation ->
            db.getList(auth.userId, id).get().addOnSuccessListener { doc ->
                val result = doc?.toObject(TravelCheckListImpl::class.java)
                if (result == null) {
                    continuation.resumeWithException(NullPointerException("Not fund"))
                    return@addOnSuccessListener
                }
                continuation.resume(result)
            }.addOnFailureListener { error -> continuation.resumeWithException(error) }

        }

    override suspend fun updateItem(item: CheckListItemImpl, listId: String, categoryId: Any?) {
        val documentReference = db.getList(auth.userId, listId)
        documentReference.get().continueWith {
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
}