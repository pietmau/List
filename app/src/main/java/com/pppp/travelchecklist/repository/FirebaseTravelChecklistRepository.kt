package com.pppp.travelchecklist.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

class FirebaseTravelChecklistRepository @Inject constructor(
    val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : TravelChecklistRepository {

    private val USERS = "users"
    private val USERS_CHECKLISTS = "user_checklists"

    override fun saveAndGet(list: List<Category>): Single<String> = Single.create { emitter ->
        getUserCheckLists()
            .add(TravelCheckListImpl(list))
            .addOnSuccessListener {
                emitter.onSuccess(it.id)
            }
            .addOnFailureListener {
                emitter.onError(Exception("Unable to save"))
            }
    }

    override fun getUserCheckList(listId: String, success: ((TravelCheckList) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        getUserCheckLists().document(listId).get().addOnSuccessListener { documentSnapshot ->
            val checkList = documentSnapshot.toObject(TravelCheckListImpl::class.java)
            if (checkList != null) {
                success?.invoke(checkList)
            } else {
                onFaiulure(failure, listId)
            }
        }.addOnFailureListener {
            onFaiulure(failure, listId)
        }
    }

    private fun onFaiulure(failure: ((Throwable) -> Unit)?, listId: String) {
        failure?.invoke(ListNotFoundException(getUserId(), listId))
    }

    private fun getUserId() = auth.uid ?: throw UserNotLoggedInException()

    private fun getUserCheckLists() =
        db.collection(USERS)
            .document(getUserId())
            .collection(USERS_CHECKLISTS)

}