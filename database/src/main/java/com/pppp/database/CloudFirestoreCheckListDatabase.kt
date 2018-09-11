package com.pppp.database

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.pppp.entities.Category
import com.pppp.entities.CheckListItem
import com.pppp.entities.Tag
import io.reactivex.Single
import javax.inject.Inject

class CloudFirestoreCheckListDatabase @Inject constructor() : CheckListDatabase {
    private val db = FirebaseFirestore.getInstance();

    override fun getTags(): Single<List<Tag>> {
        return Single.create<List<Tag>> { emitter ->
            db.collection(CheckListDatabase.TAGS).get()
                .addOnSuccessListener { querySnapshot ->
                    val result = onTagsAvailable(querySnapshot)
                    emitter.onSuccess(result)
                }
                .addOnFailureListener { exception ->
                    emitter.onError(exception)
                }
        }
    }

    override fun getItems(): Single<List<CheckListItem>> {
        return Single.create<List<CheckListItem>> { emitter ->
            db.collection(CheckListDatabase.ITEMS).get()
                .addOnSuccessListener { querySnapshot ->
                    val result = onItemsAvailable(querySnapshot)
                    emitter.onSuccess(result)
                }
                .addOnFailureListener { exception ->
                    emitter.onError(exception)
                }
        }
    }

    override fun getCategories(): Single<List<Category>> {
        return Single.create<List<Category>> { emitter ->
            db.collection(CheckListDatabase.CATEGORIES).get()
                .addOnSuccessListener { querySnapshot ->
                    val result = onCategoriesAvailable(querySnapshot)
                    emitter.onSuccess(result)
                }
                .addOnFailureListener { exception ->
                    emitter.onError(exception)
                }
        }
    }

    private fun onCategoriesAvailable(querySnapshot: QuerySnapshot?): List<Category> {
        querySnapshot ?: return emptyList()
        return querySnapshot.documents
            .map { doument ->
                val item = doument.toObject(Category::class.java)
                item.key = doument.id
                item
            }.toList()
    }

    private fun onItemsAvailable(querySnapshot: QuerySnapshot?): List<CheckListItem> {
        querySnapshot ?: return emptyList()
        return querySnapshot.documents
            .map { doument ->
                val item = doument.toObject(CheckListItem::class.java)
                item.key = doument.id
                item
            }
    }

    private fun onTagsAvailable(querySnapshot: QuerySnapshot?): List<Tag> {
        querySnapshot ?: return emptyList()
        return querySnapshot.documents.map { doument ->
            val tag = doument.toObject(Tag::class.java)
            tag.key = doument.id
            tag
        }.toList()
    }

    override fun saveExaple() {
        val item = CheckListItem()
        item.key = "this is a key"
        item.category = "this is a categlry"
        item.description = "thsi is a description"
        item.checked = true
        item.priority = 10
        item.title = "this is a title"
        item.tags = listOf("this is a tag", "this is another tag")
        db.collection(CheckListDatabase.ITEMS).add(item)
    }

    companion object {
        private const val TITLE = "title"
    }
}
