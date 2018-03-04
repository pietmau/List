package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.model.CardItemData
import io.reactivex.Observable
import io.reactivex.Observer


interface Model {
    fun subscribe(observer: Observer<List<CardItemData>>)

    fun getCards(): Observable<List<CardItemData>>
}