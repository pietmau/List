package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.model.Card
import com.pppp.travelchecklist.model.CheckList
import com.pppp.travelchecklist.model.CheckListItemData
import com.pppp.travelchecklist.model.Priority
import com.pppp.travelchecklist.model.dao.ListDao
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject


class ModelImpl(private val dao: ListDao) : Model {
    override fun getItem(cardPosition: Int, itemPosition: Int): Single<CheckListItemData> {
        TODO("not implemented")
    }

    override fun getCards(checklistId: Long): Observable<CheckList> {
        TODO("not implemented")
    }

    private var items: MutableList<CheckListItemData> = createItems()
    private var cards: MutableList<Card> = createCards()

    private val subject by lazy {
        val subject = BehaviorSubject.create<List<Card>>()
        subject.onNext(cards)
        subject
    }

    private fun createItems(): MutableList<CheckListItemData> {
        dao.getCheckLists()
        var list = mutableListOf<CheckListItemData>()
        for (i in 0..5) {
            val data = CheckListItemData("Title $i", false, Priority(5), "Description $i", 0, 0)
            list.add(data)
        }
        return list
    }

    private fun createCards(): MutableList<Card> {
        val list = mutableListOf<Card>()
        for (i in 0..5) {
            val card = Card("Card $i", items, i.toLong(), 0)
            list.add(card)
        }
        return list
    }


    override fun deleteItem(cardPosition: Int, itemPosition: Int) {
        items = createItems()
        items.removeAt(itemPosition)
        cards = createCards()
        subject.onNext(cards)
    }

    override fun onItemEdited(item: CheckListItemData, cardPosition: Int, itemPosition: Int) {
        TODO("not implemented")
    }
}