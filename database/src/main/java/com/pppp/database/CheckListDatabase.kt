package com.pppp.database

import com.pppp.entities.Category
import com.pppp.entities.CheckListItem
import com.pppp.entities.Tag
import com.pppp.entities.TagsGroup
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CheckListDatabase {
    fun getTags(): Single<List<Tag>>

    fun getItems(): Single<List<CheckListItem>>

    fun getCategories(): Single<List<Category>>

    fun subscribeToItemsAndUpdates(): Observable<List<CheckListItem>>

    fun saveItem(item: CheckListItem, key: String): Completable

    fun saveCategory(category: Category, key: String): Completable

    fun subscribeToCategoriesAndUpdates(): Observable<List<Category>>

    fun subscribeToTagsAndUpdates(): Observable<List<Tag>>

    fun saveTag(tag: Tag, key: String): Completable

    fun getTagGroups(): Single<List<TagsGroup>>

    fun subscribeToGroupsAndUpdates(): Observable<List<TagsGroup>>

    fun saveTagGroup(group: TagsGroup, key: String): Completable

    companion object {
        const val TAGS = "tags"
        const val ITEMS = "items"
        const val CATEGORIES = "categories"
        const val GROUPS = "groups"
    }

}