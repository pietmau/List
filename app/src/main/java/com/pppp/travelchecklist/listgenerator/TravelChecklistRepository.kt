package com.pppp.travelchecklist.listgenerator

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.Category
import com.pppp.entities.pokos.TravelCheckListImpl
import io.reactivex.Single
import java.lang.Exception

internal val USERS = "users"
internal val USERS_LIST = "users_lists"

interface TravelChecklistRepository {

    fun saveAndGet(list: List<Category>): Single<String>

}

class FirebaseTravelChecklistRepository(
    val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : TravelChecklistRepository {

    override fun saveAndGet(list: List<Category>): Single<String> = Single.create { emitter ->
        val userId = auth.currentUser?.uid
        if (userId == null) {
            emitter.onError(Exception("User not found"))
            return@create
        }
        db.collection(USERS)
            .document(userId)
            .collection(USERS_LIST)
            .add(TravelCheckListImpl(list))
            .addOnSuccessListener {
                emitter.onSuccess(it.id)
            }
            .addOnFailureListener {
                emitter.onError(Exception("Unable to save"))
            }
    }

}
