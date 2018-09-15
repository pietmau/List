package com.pppp.database

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.pppp.entities.Category
import com.pppp.entities.CheckListItem
import com.pppp.entities.Tag
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CloudFirestoreCheckListDatabase @Inject constructor() : CheckListDatabase {

    private val db = FirebaseFirestore.getInstance();

    override fun getTags() = Single.create<List<Tag>> { emitter ->
        db.collection(CheckListDatabase.TAGS).get()
            .addOnSuccessListener { querySnapshot ->
                emitter.onSuccess(onTagsAvailable(querySnapshot))
            }
            .addOnFailureListener { exception ->
                emitter.onError(exception)
            }
    }

    override fun getItems() =
        Single.create<List<CheckListItem>> { emitter ->
            getItremsReference().get()
                .addOnSuccessListener { querySnapshot ->
                    emitter.onSuccess(onItemsAvailable(querySnapshot))
                }
                .addOnFailureListener { exception ->
                    emitter.onError(exception)
                }
        }

    override fun getCategories() =
        Single.create<List<Category>> { emitter ->
            db.collection(CheckListDatabase.CATEGORIES).get()
                .addOnSuccessListener { querySnapshot ->
                    emitter.onSuccess(onCategoriesAvailable(querySnapshot))
                }
                .addOnFailureListener { exception ->
                    emitter.onError(exception)
                }
        }

    override fun subscribeToItemsAndUpdates() =
        Observable.create<List<CheckListItem>> { emitter ->
            getItremsReference().addSnapshotListener { snapshot, exception ->
                if (exception == null) {
                    emitter.onNext(onItemsAvailable(snapshot))
                } else {
                    emitter.onError(exception)
                }
            }
        }

    private fun getItremsReference() = db.collection(CheckListDatabase.ITEMS)

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
