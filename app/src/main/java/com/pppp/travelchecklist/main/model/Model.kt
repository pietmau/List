package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.model.CardItemData
import com.pppp.travelchecklist.model.CheckListItemData
import io.reactivex.Observable


interface Model {
    fun getCards(): Observable<List<CardItemData>>
    fun deleteItem(cardPosition: Int, itemPosition: Int)
    fun getItem(cardPosition: Int, itemPosition: Int): CheckListItemData
}