package com.pppp.database

import com.pppp.entities.Category
import com.pppp.entities.CheckListItem
import com.pppp.entities.Tag
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CheckListDatabase {
    fun getTags(): Single<List<Tag>>

    fun getItems(): Single<List<CheckListItem>>

    fun getCategories(): Single<List<Category>>

    fun subscribeToItemsAndUpdates(): Observable<List<CheckListItem>>

    fun saveItem(item: CheckListItem): Completable?

    companion object {
        const val TAGS = "tags"
        const val ITEMS = "items"
        const val CATEGORIES = "categories"
    }


    fun saveCategory(category: Category): Completable?
    fun subscribeToCategoriesAndUpdates(): Observable<List<Category>>?
    fun subscribeToTagsAndUpdates(): Observable<List<Tag>>?
    fun saveTag(tag: Tag): Completable?
}