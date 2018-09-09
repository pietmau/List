package com.pppp.database

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.pppp.database.CheckListDatabase.Companion.TAGS
import com.pppp.entities.CheckListItem
import com.pppp.entities.Tag
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import javax.inject.Inject

class CloudFirestoreCheckListDatabase @Inject constructor() : CheckListDatabase {
    private val db = FirebaseFirestore.getInstance();
    private val tags: SingleSubject<List<Tag>> = SingleSubject.create()
    private val items: SingleSubject<List<CheckListItem>> = SingleSubject.create()

    override fun getTags(): Single<List<Tag>> {
        db.collection(TAGS).get()
            .addOnSuccessListener { querySnapshot -> onTagsAvailable(querySnapshot) }
        return tags
    }

    override fun getItems(): Single<List<CheckListItem>> {
        db.collection(CheckListDatabase.ITEMS).get()
            .addOnSuccessListener { querySnapshot ->
                onItemsAvailable(querySnapshot)
            }
        return items
    }

    override fun start() {/*NoOp*/
    }

    private fun onItemsAvailable(querySnapshot: QuerySnapshot?) {
        querySnapshot ?: return
        val items = querySnapshot.documents
            .map { doument ->
                val item = doument.toObject(CheckListItem::class.java)
                item.key = doument.id
                item
            }
        this.items.onSuccess(items)
    }

    private fun onTagsAvailable(querySnapshot: QuerySnapshot?) {
        querySnapshot ?: return
        val tags = querySnapshot.documents.map { doument ->
            val tag = doument.toObject(Tag::class.java)
            tag.key = doument.id
            tag
        }.toList()
        this.tags.onSuccess(tags)
    }

    override fun stop() {/*NoOp*/
    }

    companion object {
        private const val TITLE = "title"
    }
}
