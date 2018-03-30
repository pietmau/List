package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.model.CardItemData
import io.reactivex.Observable


interface Model {
    fun getCards(): Observable<List<CardItemData>>
    fun onItemDeleteRequested(cardPosition: Int, itemPosition: Int)
}