package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.main.view.TravelListView
import com.pppp.travelchecklist.model.CardItemData
import com.pppp.travelchecklist.model.CheckListItemData
import com.pppp.travelchecklist.model.Priority
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject


class ModelImpl : Model {
    override fun getItem(position: TravelListView.Action.Position): CheckListItemData = cards[position.cardIndex].items[position.itemIndex]

    private var items: MutableList<CheckListItemData> = createItems()
    private var cards: MutableList<CardItemData> = createList()

    private val cardsSubject: Subject<List<CardItemData>> by lazy {
        var subject = BehaviorSubject.create<List<CardItemData>>()
        subject.onNext(cards)
        subject
    }

    override fun getCards(): List<CardItemData> = cards

    override fun getCardsAsObservable(): Observable<List<CardItemData>> = cardsSubject

    override fun subscribe(observer: Observer<List<CardItemData>>) {
        cardsSubject.subscribe(observer)
    }

    private fun createList(): MutableList<CardItemData> {
        val list = mutableListOf<CardItemData>()
        for (i in 0..10) {
            val card = CardItemData("Card $i", items)
            list.add(card)
        }
        return list// TODO to immutable???
    }

    private fun createItems(): MutableList<CheckListItemData> {
        val list = mutableListOf<CheckListItemData>()
        for (i in 0..10) {
            val data = CheckListItemData("Item $i", false, Priority(5))
            list.add(data)
        }
        return list
    }

    override fun deleteItemAtPosition(position: TravelListView.Action.Position) {
        var temp = items
        temp.removeAt(position.itemIndex)
        cards[position.cardIndex]
        cardsSubject.onNext(cards)
    }
}