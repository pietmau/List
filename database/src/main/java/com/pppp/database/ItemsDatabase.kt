package com.pppp.database

import com.pppp.entities.pokos.CheckListItemImpl
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ItemsDatabase {

    fun getItems(): Single<List<CheckListItemImpl>>
    fun subscribeToItemsAndUpdates(): Observable<List<CheckListItemImpl>>
    fun saveItem(item: CheckListItemImpl, key: String): Completable
}
