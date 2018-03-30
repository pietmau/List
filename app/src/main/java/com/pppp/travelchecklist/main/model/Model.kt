package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.main.view.TravelListView
import com.pppp.travelchecklist.model.CardItemData
import com.pppp.travelchecklist.model.CheckListItemData
import io.reactivex.Observable
import io.reactivex.Observer


interface Model {
    fun subscribe(observer: Observer<List<CardItemData>>)
    fun getCardsAsObservable(): Observable<List<CardItemData>>
    fun getCards(): List<CardItemData>
    fun deleteItemAtPosition(position: TravelListView.Action.Position)
    fun getItem(position: TravelListView.Action.Position): CheckListItemData

}