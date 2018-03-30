package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.main.view.TravelListView
import com.pppp.travelchecklist.model.CardItemData
import com.pppp.travelchecklist.model.CheckListItemData
import com.pppp.travelchecklist.model.Priority
import io.reactivex.Observable
import io.reactivex.Observer


class ModelImpl : Model {
    override fun getItem(position: TravelListView.Action.Position): CardItemData = CardItemData("", emptyList())

    override fun getCards(): List<CardItemData> = createList()

    override fun getCardsAsObservable(): Observable<List<CardItemData>> = Observable.just(createList())

    override fun subscribe(observer: Observer<List<CardItemData>>) {
        val list = createList()
        Observable.just(list).subscribe(observer)
    }

    private fun createList(): List<CardItemData> {
        val list = mutableListOf<CardItemData>()
        for (i in 0..10) {
            val items = createItems()
            val card = CardItemData("Card $i", items)
            list.add(card)
        }
        return list// TODO to immutable???
    }

    private fun createItems(): List<CheckListItemData> {
        val list = mutableListOf<CheckListItemData>()
        for (i in 0..10) {
            val data = CheckListItemData("Item $i", false, Priority(5))
            list.add(data)
        }
        return list
    }
}