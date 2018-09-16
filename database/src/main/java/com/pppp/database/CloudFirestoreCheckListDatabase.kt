package com.pppp.database

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.pppp.entities.Category
import com.pppp.entities.CheckListItem
import com.pppp.entities.Tag
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CloudFirestoreCheckListDatabase
@Inject constructor(private val db: FirebaseFirestore = FirebaseFirestore.getInstance()) :
    CheckListDatabase {

    override fun getTags() = Single.create<List<Tag>> { emitter ->
        getTagsReference().get()
            .addOnSuccessListener { querySnapshot ->
                emitter.onSuccess(onTagsAvailable(querySnapshot))
            }
            .addOnFailureListener { exception ->
                emitter.onError(exception)
            }
    }

    override fun getItems() =
        Single.create<List<CheckListItem>> { emitter ->
            getItemsReference().get()
                .addOnSuccessListener { querySnapshot ->
                    emitter.onSuccess(onItemsAvailable(querySnapshot))
                }
                .addOnFailureListener { exception ->
                    emitter.onError(exception)
                }
        }

    override fun getCategories() =
        Single.create<List<Category>> { emitter ->
            getCollectionReference().get()
                .addOnSuccessListener { querySnapshot ->
                    emitter.onSuccess(onCategoriesAvailable(querySnapshot))
                }
                .addOnFailureListener { exception ->
                    emitter.onError(exception)
                }
        }

    override fun subscribeToItemsAndUpdates() =
        Observable.create<List<CheckListItem>> { emitter ->
            getItemsReference().addSnapshotListener { snapshot, exception ->
                if (exception == null) {
                    emitter.onNext(onItemsAvailable(snapshot))
                } else {
                    emitter.onError(exception)
                }
            }
        }

    override fun subscribeToCategoriesAndUpdates() =
        Observable.create<List<Category>> { emitter ->
            getCollectionReference().addSnapshotListener { snapshot, exception ->
                if (exception == null) {
                    emitter.onNext(onCategoriesAvailable(snapshot))
                } else {
                    emitter.onError(exception)
                }
            }
        }

    override fun subscribeToTagsAndUpdates() =
        Observable.create<List<Tag>> { emitter ->
            getTagsReference().addSnapshotListener { snapshot, exception ->
                if (exception == null) {
                    emitter.onNext(onTagsAvailable(snapshot))
                } else {
                    emitter.onError(exception)
                }
            }
        }

    override fun saveItem(item: CheckListItem) =
        Completable.create { emitter ->
            getItemsReference().document(item.title).set(item).addOnSuccessListener {
                emitter.onComplete()
            }.addOnFailureListener { error ->
                emitter.onError(error)
            }
        }

    override fun saveCategory(category: Category) =
        Completable.create { emitter ->
            getCollectionReference().document(category.title).set(category).addOnSuccessListener {
                emitter.onComplete()
            }.addOnFailureListener { error ->
                emitter.onError(error)
            }
        }

    override fun saveTag(tag: Tag) =
        Completable.create { emitter ->
            getTagsReference().document(tag.title).set(tag).addOnSuccessListener {
                emitter.onComplete()
            }.addOnFailureListener { error ->
                emitter.onError(error)
            }
        }

    private fun getTagsReference() = db.collection(CheckListDatabase.TAGS)

    private fun getItemsReference() = db.collection(CheckListDatabase.ITEMS)

    private fun getCollectionReference() = db.collection(CheckListDatabase.CATEGORIES)

    private fun onCategoriesAvailable(querySnapshot: QuerySnapshot?) =
        querySnapshot?.toObjects(Category::class.java) ?: emptyList()

    private fun onItemsAvailable(querySnapshot: QuerySnapshot?) =
        querySnapshot?.toObjects(CheckListItem::class.java) ?: emptyList()

    private fun onTagsAvailable(querySnapshot: QuerySnapshot?) =
        querySnapshot?.toObjects(Tag::class.java) ?: emptyList()

}
