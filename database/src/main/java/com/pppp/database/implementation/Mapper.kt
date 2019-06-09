package com.pppp.database.implementation

import com.google.firebase.firestore.QuerySnapshot
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TagImpl
import com.pppp.entities.pokos.TagsGroupImpl


class Mapper {
    fun categories(querySnapshot: QuerySnapshot) =
        querySnapshot
            .documents
            .map { doc -> doc to doc.toObject(CategoryImpl::class.java) }
            .filter { (_, item) -> item != null }
            .map { (doc, category) ->
                category!!.id = doc.id.toLong()
                category!!
            }

    fun items(querySnapshot: QuerySnapshot) =
        querySnapshot
            .documents
            .map { item -> item to item.toObject(CheckListItemImpl::class.java) }
            .filter { (_, item) -> item != null }
            .map { (doc, item) ->
                item!!.id = doc.id.toLong()
                item!!
            }

    fun tags(querySnapshot: QuerySnapshot) =
        querySnapshot
            .documents
            .map { doc -> doc to doc.toObject(TagImpl::class.java) }
            .filter { (_, item) -> item != null }
            .map { (doc, tag) ->
                tag!!.id = doc.id.toLong()
                tag!!
            }

    fun groups(querySnapshot: QuerySnapshot) =
        querySnapshot
            .documents
            .map { doc -> doc to doc.toObject(TagsGroupImpl::class.java) }
            .filter { (_, item) -> item != null }
            .map { (doc, group) ->
                group!!.id = doc.id.toLong()
                group!!
            }
}