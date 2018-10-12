package com.pppp.database

import com.pppp.entities.pokos.CheckListItem
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ItemsDatabase {

    fun getItems(): Single<List<CheckListItem>>
    fun subscribeToItemsAndUpdates(): Observable<List<CheckListItem>>
    fun saveItem(item: CheckListItem, key: String): Completable
}
