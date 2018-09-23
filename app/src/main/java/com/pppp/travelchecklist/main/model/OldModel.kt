package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.model.CheckList
import com.pppp.entities.CheckListItem
import io.reactivex.Observable
import io.reactivex.Single


interface OldModel {
    fun getCards(checklistId: Long): Observable<CheckList>
    fun deleteItem(cardPosition: Int, itemPosition: Int)
    fun getItem(cardPosition: Int, itemPosition: Int): Single<CheckListItem>
    fun onItemEdited(item: CheckListItem, cardPosition: Int, itemPosition: Int)
}