package com.pppp.travelchecklist.main.model

import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.travelchecklist.model.dao.ListDao
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject


class OldModelImpl(private val dao: ListDao) : OldModel {
    override fun getItem(cardPosition: Int, itemPosition: Int): Single<CheckListItemImpl> {
        TODO("not implemented")
    }

    override fun getCards(checklistId: Long): Observable<CheckListImpl> {
        TODO("not implemented")
    }

    private var items: MutableList<CheckListItemImpl> = createItems()
    private var categories: MutableList<CategoryImpl> = createCards()

    private val subject by lazy {
        val subject = BehaviorSubject.create<List<CategoryImpl>>()
        subject.onNext(categories)
        subject
    }

    private fun createItems(): MutableList<CheckListItemImpl> {
        dao.getCheckLists()
        var list = mutableListOf<CheckListItemImpl>()
        for (i in 0..5) {
            val data =
                CheckListItemImpl("Title $i", false, 5, "Description $i", CategoryImpl(), emptyList())
            list.add(data)
        }
        return list
    }

    private fun createCards(): MutableList<CategoryImpl> {
        val list = mutableListOf<CategoryImpl>()
        for (i in 0..5) {
            val card = CategoryImpl("CategoryImpl $i", null, items = items)
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

    override fun onItemEdited(item: CheckListItemImpl, cardPosition: Int, itemPosition: Int) {
        TODO("not implemented")
    }
}