package com.pppp.database

import com.pppp.entities.Category
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CategoriesDatabase {

    fun getCategories(): Single<List<Category>>
    fun saveCategory(category: Category, key: String): Completable
    fun subscribeToCategoriesAndUpdates(): Observable<List<Category>>
}
