package com.pppp.database.implementation

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.pppp.database.CheckListDatabase
import com.pppp.entities.Category
import com.pppp.entities.CheckListItem
import com.pppp.entities.Tag
import com.pppp.entities.TagsGroup
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class CloudFirestoreCheckListDatabase constructor(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val mapper: Mapper = Mapper()
) :
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

    override fun getTagGroups() =
        Single.create<List<TagsGroup>> { emitter ->
            getTagGroupsReference().get()
                .addOnSuccessListener { querySnapshot ->
                    emitter.onSuccess(onGroupsAvailable(querySnapshot))
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

    override fun subscribeToGroupsAndUpdates() =
        Observable.create<List<TagsGroup>> { emitter ->
            getTagsReference().addSnapshotListener { snapshot, exception ->
                if (exception == null) {
                    emitter.onNext(onGroupsAvailable(snapshot))
                } else {
                    emitter.onError(exception)
                }
            }
        }

    override fun saveItem(item: CheckListItem, id: String) =
        Completable.create { emitter ->
            getItemsReference().document(id).set(item).addOnSuccessListener {
                emitter.onComplete()
            }.addOnFailureListener { error ->
                emitter.onError(error)
            }.addOnCompleteListener {

            }
        }

    override fun saveCategory(category: Category, id: String) =
        Completable.create { emitter ->
            getCollectionReference().document(id).set(category).addOnSuccessListener {
                emitter.onComplete()
            }.addOnFailureListener { error ->
                emitter.onError(error)
            }
        }

    override fun saveTag(tag: Tag, id: String) =
        Completable.create { emitter ->
            getTagsReference().document(id).set(tag).addOnSuccessListener {
                emitter.onComplete()
            }.addOnFailureListener { error ->
                emitter.onError(error)
            }
        }

    override fun saveTagGroup(group: TagsGroup, id: String) =
        Completable.create { emitter ->
            getTagGroupsReference().document(id).set(group).addOnSuccessListener {
                emitter.onComplete()
            }.addOnFailureListener { error ->
                emitter.onError(error)
            }
        }

    private fun getTagGroupsReference() = db.collection(CheckListDatabase.GROUPS)

    private fun getTagsReference() = db.collection(CheckListDatabase.TAGS)

    private fun getItemsReference() = db.collection(CheckListDatabase.ITEMS)

    private fun getCollectionReference() = db.collection(CheckListDatabase.CATEGORIES)

    private fun onCategoriesAvailable(querySnapshot: QuerySnapshot) =
        mapper.categories(querySnapshot)

    private fun onItemsAvailable(querySnapshot: QuerySnapshot) = mapper.items(querySnapshot)

    private fun onTagsAvailable(querySnapshot: QuerySnapshot) = mapper.tags(querySnapshot)

    private fun onGroupsAvailable(querySnapshot: QuerySnapshot) = mapper.groups(querySnapshot)


}
