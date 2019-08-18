package com.pppp.travelchecklist.list.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.repository.ListNotFoundException
import com.pppp.travelchecklist.repository.SingleCheckListRepository
import com.pppp.travelchecklist.repository.USERS
import com.pppp.travelchecklist.repository.USERS_CHECKLISTS
import com.pppp.travelchecklist.repository.UserNotLoggedInException

class FirebaseSingleCheckListRepository(
    val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

) : SingleCheckListRepository {

    override fun updateList(listId: String, travelCheckList: TravelCheckList) {
        db.collection(USERS)
            .document(getUserId())
            .collection(USERS_CHECKLISTS).document(listId).update("categories", travelCheckList.categories)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }

    override fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        db.collection(USERS)
            .document(getUserId())
            .collection(USERS_CHECKLISTS).document(listId).addSnapshotListener { documentSnapshot, exception ->
                if (exception != null) {
                    failure?.invoke(exception)
                } else {
                    val checkList = documentSnapshot?.toObject(TravelCheckListImpl::class.java)
                    if (checkList != null) {
                        success?.invoke(checkList)
                    } else {
                        onFailure(failure, listId)
                    }
                }
            }
    }

    override fun getUserCheckList(listId: String, success: ((TravelCheckList) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        db.collection(USERS)
            .document(getUserId())
            .collection(USERS_CHECKLISTS).document(listId).get()
            .addOnFailureListener { failure?.invoke(it) }.addOnSuccessListener { documentSnapshot ->
                val checkList = documentSnapshot?.toObject(TravelCheckListImpl::class.java)
                if (checkList != null) {
                    success?.invoke(checkList)
                } else {
                    onFailure(failure, listId)
                }
            }
    }

    private fun onFailure(failure: ((Throwable) -> Unit)?, listId: String) = failure?.invoke(ListNotFoundException(getUserId(), listId))

    private fun getUserId() = auth.uid ?: throw UserNotLoggedInException()
}