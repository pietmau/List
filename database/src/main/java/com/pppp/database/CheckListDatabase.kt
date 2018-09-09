package com.pppp.database

import com.pppp.entities.Category
import com.pppp.entities.CheckListItem
import com.pppp.entities.Tag
import io.reactivex.Single

interface CheckListDatabase {

    fun getTags(): Single<List<Tag>>

    fun start()

    fun stop()

    companion object {
        const val TAGS = "tags"
        const val ITEMS = "items"
        const val CATEGORIES = "categories"
    }

    fun getItems(): Single<List<CheckListItem>>
    fun saveExaple()
    fun getCategories(): Single<List<Category>>
}