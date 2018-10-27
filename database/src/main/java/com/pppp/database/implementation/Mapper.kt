package com.pppp.database.implementation

import com.google.firebase.firestore.QuerySnapshot
import com.pppp.entities.pokos.Category
import com.pppp.entities.pokos.CheckListItem
import com.pppp.entities.pokos.Tag
import com.pppp.entities.pokos.TagsGroup


class Mapper {
    fun categories(querySnapshot: QuerySnapshot) =
        querySnapshot
            .documents
            .map { doc -> doc to doc.toObject(Category::class.java) }
            .filter { (_, item) -> item != null }
            .map { (doc, category) ->
                category!!.id = doc.id
                category!!
            }

    fun items(querySnapshot: QuerySnapshot) =
        querySnapshot
            .documents
            .map { item -> item to item.toObject(CheckListItem::class.java) }
            .filter { (_, item) -> item != null }
            .map { (doc, item) ->
                item!!.id = doc.id
                item!!
            }

    fun tags(querySnapshot: QuerySnapshot) =
        querySnapshot
            .documents
            .map { doc -> doc to doc.toObject(Tag::class.java) }
            .filter { (_, item) -> item != null }
            .map { (doc, tag) ->
                tag!!.id = doc.id
                tag!!
            }

    fun groups(querySnapshot: QuerySnapshot) =
        querySnapshot
            .documents
            .map { doc -> doc to doc.toObject(TagsGroup::class.java) }
            .filter { (_, item) -> item != null }
            .map { (doc, group) ->
                group!!.id = doc.id
                group!!
            }
}