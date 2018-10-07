package com.pppp.database.implementation

import com.google.firebase.firestore.QuerySnapshot
import com.pppp.database.pokos.CategoryImpl
import com.pppp.database.pokos.CheckListItemImpl
import com.pppp.database.pokos.TagImpl
import com.pppp.database.pokos.TagsGroupImpl


class Mapper {
    fun categories(querySnapshot: QuerySnapshot) =
        querySnapshot
            .documents
            .map { doc -> doc to doc.toObject(CategoryImpl::class.java) }
            .map { (doc, category) ->
                category.id = doc.id
                category
            }

    fun items(querySnapshot: QuerySnapshot) =
        querySnapshot
            .documents
            .map { item -> item to item.toObject(CheckListItemImpl::class.java) }
            .map { (doc, item) ->
                item.id = doc.id
                item
            }

    fun tags(querySnapshot: QuerySnapshot) =
        querySnapshot
            .documents
            .map { doc -> doc to doc.toObject(TagImpl::class.java) }
            .map { (doc, tag) ->
                tag.id = doc.id
                tag
            }

    fun groups(querySnapshot: QuerySnapshot) =
        querySnapshot
            .documents
            .map { doc -> doc to doc.toObject(TagsGroupImpl::class.java) }
            .map { (doc, group) ->
                group.id = doc.id
                group
            }
}