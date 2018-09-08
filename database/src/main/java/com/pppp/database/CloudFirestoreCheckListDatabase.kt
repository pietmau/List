package com.pppp.database

import com.google.firebase.firestore.FirebaseFirestore
import com.pppp.database.CheckListDatabase.Companion.TAGS
import com.pppp.entities.Tag
import io.reactivex.Single
import javax.inject.Inject

class CloudFirestoreCheckListDatabase @Inject constructor() : CheckListDatabase {
    private val db = FirebaseFirestore.getInstance();

    override fun getTags(): Single<List<Tag>> =
        Single.fromCallable {
            val result = db.collection(TAGS).get().result
            emptyList<Tag>()
        }
}