package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.model.Card
import com.pppp.travelchecklist.model.CheckListItemData
import io.reactivex.Observable


interface Model {
    fun getCards(): Observable<List<Card>>
    fun deleteItem(cardPosition: Int, itemPosition: Int)
    fun getItem(cardPosition: Int, itemPosition: Int): CheckListItemData
    fun onItemEdited(item: CheckListItemData, cardPosition: Int, itemPosition: Int)
}