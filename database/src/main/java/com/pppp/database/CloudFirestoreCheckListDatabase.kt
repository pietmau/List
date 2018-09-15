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

    private fun onCategoriesAvailable(querySnapshot: QuerySnapshot?) =
        querySnapshot?.toObjects(Category::class.java) ?: emptyList()

    private fun onItemsAvailable(querySnapshot: QuerySnapshot?) =
        querySnapshot?.toObjects(CheckListItem::class.java) ?: emptyList()

    private fun onTagsAvailable(querySnapshot: QuerySnapshot?) =
        querySnapshot?.toObjects(Tag::class.java) ?: emptyList()


    override fun saveExaple() {

    }

    companion object {
        private const val TITLE = "title"
    }
}
