package com.pppp.travelchecklist.list.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.repository.ListNotFoundException
import com.pppp.travelchecklist.repository.SingleCheckListRepository
import com.pppp.travelchecklist.repository.UserNotLoggedInException
import com.pppp.travelchecklist.repository.getCheckListsById

class FirebaseSingleCheckListRepository(
    val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

) : SingleCheckListRepository {

    override fun updateList(listId: String, travelCheckList: TravelCheckList) {
        db.getCheckListsById(getUserId(), listId).update("categories", travelCheckList.categories)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }

    override fun getUserCheckListAndUpdates(listId: String, success: ((TravelCheckList) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        db.getCheckListsById(getUserId(), listId).addSnapshotListener { documentSnapshot, exception ->
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

    private fun onFailure(failure: ((Throwable) -> Unit)?, listId: String) = failure?.invoke(ListNotFoundException(getUserId(), listId))

    private fun getUserId() = auth.uid ?: throw UserNotLoggedInException()
}