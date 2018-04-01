package com.pppp.travelchecklist.main.model

import com.pppp.travelchecklist.model.CheckList
import com.pppp.travelchecklist.model.CheckListItemData
import com.pppp.travelchecklist.model.SimpleObserver
import com.pppp.travelchecklist.model.dao.ListDao
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject


class BetterModelImpl(
        private val dao: ListDao,
        val io: Scheduler,
        val ui: Scheduler) : Model {

    private val subject = BehaviorSubject.create<CheckList>()
    private var checklistId: Long? = null

    override fun getCards(checklistId: Long): Observable<CheckList> {
        this.checklistId = checklistId
        notifyDatabaseChanged()
        return subject
    }

    override fun deleteItem(cardPosition: Int, itemPosition: Int) {
        subject
                .map { it.cards }
                .map { it.get(cardPosition) }
                .map { it.items.get(itemPosition) }
                .firstOrError()
                .map { dao.deleteItem(it) }
                .filter { it > 0 }
                .subscribeOn(io)
                .observeOn(ui)
                .subscribe({ notifyDatabaseChanged() }, {})
    }

    override fun getItem(cardPosition: Int, itemPosition: Int): Single<CheckListItemData> {
        return subject
                .map { it.cards }
                .map { it.get(cardPosition) }
                .map { it.items.get(itemPosition) }
                .firstOrError()
                .subscribeOn(io)
                .observeOn(ui)
    }

    override fun onItemEdited(item: CheckListItemData, cardPosition: Int, itemPosition: Int) {
        Observable.fromCallable {
            dao.editItem(item)
        }.
                filter { it > 0 }
                .subscribeOn(io)
                .observeOn(ui)
                .doOnNext { notifyDatabaseChanged() }
                .subscribe(object : SimpleObserver<Int>() {})
    }

    private fun notifyDatabaseChanged() {
        checklistId?.let { subject.onNext(dao.getCheckListById(it)) }
    }
}