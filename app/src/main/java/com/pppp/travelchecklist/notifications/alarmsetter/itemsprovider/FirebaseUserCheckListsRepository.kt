package com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.list.model.getList
import com.pppp.travelchecklist.list.model.getUserLists
import com.pppp.travelchecklist.list.model.userId
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseUserCheckListsRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : UserCheckListsRepository {

    override suspend fun getUserCheckLists(): List<TravelCheckList> =
        suspendCoroutine { continuation ->
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

    override suspend fun getListById(listId: String): TravelCheckList {
        return suspendCoroutine { continuation ->
            db.getList(auth.userId, listId)
                .addSnapshotListener { document, error ->
                    if (error != null) {
                        continuation.resumeWithException(error)
                        return@addSnapshotListener
                    }
                    val result = document?.toObject(TravelCheckListImpl::class.java)
                    if (result == null) {
                        continuation.resumeWithException(NullPointerException("Not fund"))
                        return@addSnapshotListener
                    }
                    continuation.resume(result)
                }
        }
    }
}