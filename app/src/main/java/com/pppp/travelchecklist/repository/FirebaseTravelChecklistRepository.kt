package com.pppp.travelchecklist.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import io.reactivex.Single
import java.lang.Exception

class FirebaseTravelChecklistRepository(
    val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : TravelChecklistRepository {

    override fun saveAndGet(list: List<Category>, name: String): Single<String> = Single.create { emitter ->
        db.getAllUserCheckLists(getUserId())
            .add(TravelCheckListImpl(list as List<CategoryImpl>, name))
            .addOnSuccessListener {
                emitter.onSuccess(it.id)
            }
            .addOnFailureListener {
                emitter.onError(Exception("Unable to save"))
            }
    }

    override fun getUserCheckListById(listId: String, success: ((TravelCheckList) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        db.getCheckListsById(getUserId(), listId).get().addOnSuccessListener { documentSnapshot ->
            val checkList = documentSnapshot.toObject(TravelCheckListImpl::class.java)
            if (checkList != null) {
                success?.invoke(checkList)
            } else {
                onFailure(failure, listId)
            }
        }.addOnFailureListener {
            onFailure(failure, listId)
        }
    }

    override fun getUsersListsAndUpdates(success: ((List<TravelCheckList>) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        db.collection(USERS)
            .document(getUserId())
            .collection(USERS_CHECKLISTS).addSnapshotListener { snapshot, error ->
                if (error != null) {
                    failure?.invoke(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val result = snapshot.documents
                        .map { documentSnapshot ->
                            documentSnapshot.toObject(TravelCheckListImpl::class.java)
                        }.filterNotNull()
                    success?.invoke(result)
                }

            }
    }

    override fun setName(listId: String, name: String?) {
        db.getCheckListsById(getUserId(), listId).update("name", name)
            .addOnSuccessListener { }
            .addOnFailureListener { }
    }

    private fun onFailure(failure: ((Throwable) -> Unit)?, listId: String) = failure?.invoke(ListNotFoundException(getUserId(), listId))

    private fun getUserId() = auth.uid ?: throw UserNotLoggedInException()

}