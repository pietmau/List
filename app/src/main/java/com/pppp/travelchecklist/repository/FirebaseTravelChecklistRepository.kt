package com.pppp.travelchecklist.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.newlist.presenter.Model
import io.reactivex.Single
import java.lang.Exception

private const val LAST_VISITED_LIST = "last_visited_list"
private const val CATEGORIES = "categories"

class FirebaseTravelChecklistRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val mapper: TravelCheckListMapper = TravelCheckListMapperImpl
) : TravelChecklistRepository {

    override fun saveLastVisitedList(listId: String) {
        user()
            .set(mapOf(LAST_VISITED_LIST to listId), SetOptions.merge())
    }

    override fun getLastVisitedList(success: ((String?) -> Unit)?, failure: ((Throwable?) -> Unit)?) {
        user().get()
            .addOnSuccessListener {
                success?.invoke(it[LAST_VISITED_LIST] as? String)
            }
            .addOnFailureListener {
                failure?.invoke(it)
            }
    }

    override fun saveAndGet(list: List<Category>, model: Model): Single<String> = Single.create { emitter ->
        userChecklists()
            .add(mapper.map(model))
            .addOnSuccessListener { travelList ->
                val collection = travelList.collection(CATEGORIES)
                list.forEach { collection.add(it) }
                emitter.onSuccess(travelList.id)
            }
            .addOnFailureListener {
                emitter.onError(Exception("Unable to save"))
            }
    }

    override fun getUserCheckListById(listId: String, success: ((TravelCheckList) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        userCheckListById(listId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val checkList = documentSnapshot.toObject(TravelCheckListImpl::class.java)
                userCheckListById(listId).collection(CATEGORIES)
                    .addSnapshotListener { querySnapshot, firebaseFirestoreException -> }

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
        userChecklists()
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    failure?.invoke(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val result = snapshot.documents
                        .map { toTravelChecklist(it) }
                        .filterNotNull()
                    success?.invoke(result)
                }
            }
    }

    private fun toTravelChecklist(documentSnapshot: DocumentSnapshot) =
        documentSnapshot.toObject(TravelCheckListImpl::class.java)?.apply {
            id = documentSnapshot.id
        }

    override fun setName(listId: String, name: String?) {
        userCheckListById(listId).update("name", name)
    }

    private fun userCheckListById(listId: String) = userChecklists().document(listId)

    private fun userChecklists() = user().collection(USERS_CHECKLISTS)

    private fun user() = db.collection(USERS).document(getUserId())

    private fun onFailure(failure: ((Throwable) -> Unit)?, listId: String) = failure?.invoke(ListNotFoundException(getUserId(), listId))

    private fun getUserId() = auth.uid ?: throw UserNotLoggedInException()

}