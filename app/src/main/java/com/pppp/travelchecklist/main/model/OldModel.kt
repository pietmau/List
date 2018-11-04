package com.pppp.travelchecklist.main.model

import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.CheckListItemImpl
import io.reactivex.Observable
import io.reactivex.Single


interface OldModel {
    fun getCards(checklistId: Long): Observable<CheckListImpl>
    fun deleteItem(cardPosition: Int, itemPosition: Int)
    fun getItem(cardPosition: Int, itemPosition: Int): Single<CheckListItemImpl>
    fun onItemEdited(item: CheckListItemImpl, cardPosition: Int, itemPosition: Int)
}