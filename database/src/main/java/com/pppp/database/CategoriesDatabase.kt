package com.pppp.database

import com.pppp.entities.pokos.CategoryImpl
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CategoriesDatabase {

    fun getCategories(): Single<List<CategoryImpl>>
    fun saveCategory(category: CategoryImpl, key: String): Completable
    fun subscribeToCategoriesAndUpdates(): Observable<List<CategoryImpl>>
}
