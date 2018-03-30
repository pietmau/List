package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.model.CardItemData
import com.pppp.travelchecklist.model.CheckListItemData
import com.pppp.travelchecklist.model.Priority
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject


class ModelImpl : Model {
    private var items: MutableList<CheckListItemData> = createItems()
    private var cards: MutableList<CardItemData> = createCards()

    private val subject by lazy {
        val subject = BehaviorSubject.create<List<CardItemData>>()
        subject.onNext(cards)
        subject
    }

    private fun createItems(): MutableList<CheckListItemData> {
        var list = mutableListOf<CheckListItemData>()
        for (i in 0..5) {
            val data = CheckListItemData("Item $i", false, Priority(5))
            list.add(data)
        }
        return list
    }

    private fun createCards(): MutableList<CardItemData> {
        val list = mutableListOf<CardItemData>()
        for (i in 0..5) {
            val card = CardItemData("Card $i", items, i.toLong())
            list.add(card)
        }
        return list
    }


    override fun getCards(): Observable<List<CardItemData>> = subject

    override fun onItemDeleteRequested(cardPosition: Int, itemPosition: Int) {
        items = createItems()
        items.removeAt(itemPosition)
        cards = createCards()
        subject.onNext(cards)
    }

}