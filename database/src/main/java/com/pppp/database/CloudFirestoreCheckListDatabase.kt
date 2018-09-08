package com.pppp.database

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.pppp.database.CheckListDatabase.Companion.TAGS
import com.pppp.entities.Tag
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import javax.inject.Inject

class CloudFirestoreCheckListDatabase @Inject constructor() : CheckListDatabase {
    private val db = FirebaseFirestore.getInstance();
    private var registration: ListenerRegistration? = null
    private val subject: SingleSubject<List<Tag>> = SingleSubject.create()

    override fun getTags(): Single<List<Tag>> = subject

    override fun start() {
        registration = db.collection(TAGS)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                onDataAvailable(querySnapshot)
            }
    }

    private fun onDataAvailable(querySnapshot: QuerySnapshot?) {
        querySnapshot ?: return
        subject
            .onSuccess(querySnapshot.documents
                .map { document -> Tag(document[TITLE] as String, document.id) }
                .toList())
    }

    override fun stop() {
        registration?.remove()
    }

    companion object {
        private const val TITLE = "title"
    }
}