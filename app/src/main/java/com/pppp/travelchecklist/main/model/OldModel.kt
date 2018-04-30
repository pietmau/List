package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.model.CheckList
import com.pppp.travelchecklist.model.CheckListItemData
import io.reactivex.Observable
import io.reactivex.Single


interface OldModel {
    fun getCards(checklistId: Long): Observable<CheckList>
    fun deleteItem(cardPosition: Int, itemPosition: Int)
    fun getItem(cardPosition: Int, itemPosition: Int): Single<CheckListItemData>
    fun onItemEdited(item: CheckListItemData, cardPosition: Int, itemPosition: Int)
}