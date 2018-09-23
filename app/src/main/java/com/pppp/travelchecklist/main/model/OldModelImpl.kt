package com.pppp.travelchecklist.main.model

import com.pppp.entities.Category
import com.pppp.travelchecklist.model.CheckList
import com.pppp.entities.CheckListItem

import com.pppp.travelchecklist.model.dao.ListDao
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject


class OldModelImpl(private val dao: ListDao) : OldModel {
    override fun getItem(cardPosition: Int, itemPosition: Int): Single<CheckListItem> {
        TODO("not implemented")
    }

    override fun getCards(checklistId: Long): Observable<CheckList> {
        TODO("not implemented")
    }

    private var items: MutableList<CheckListItem> = createItems()
    private var categories: MutableList<Category> = createCards()

    private val subject by lazy {
        val subject = BehaviorSubject.create<List<Category>>()
        subject.onNext(categories)
        subject
    }

    private fun createItems(): MutableList<CheckListItem> {
        dao.getCheckLists()
        var list = mutableListOf<CheckListItem>()
        for (i in 0..5) {
            val data = CheckListItem("Title $i", false, Priority(5), "Description $i", 0, 0)
            list.add(data)
        }
        return list
    }

    private fun createCards(): MutableList<Category> {
        val list = mutableListOf<Category>()
        for (i in 0..5) {
            val card = Category("Category $i", items, i.toLong(), 0)
            list.add(card)
        }
        return list
    }


    override fun deleteItem(cardPosition: Int, itemPosition: Int) {
        items = createItems()
        items.removeAt(itemPosition)
        categories = createCards()
        subject.onNext(categories)
    }

    override fun onItemEdited(item: CheckListItem, cardPosition: Int, itemPosition: Int) {
        TODO("not implemented")
    }
}