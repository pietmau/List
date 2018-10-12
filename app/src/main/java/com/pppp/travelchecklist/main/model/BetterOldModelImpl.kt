package com.pppp.travelchecklist.main.model

import com.pppp.entities.pokos.CheckList
import com.pppp.entities.pokos.CheckListItem
import com.pppp.travelchecklist.model.SimpleObserver
import com.pppp.travelchecklist.model.dao.ListDao
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject


class BetterOldModelImpl(
        private val dao: ListDao,
        val io: Scheduler,
        val ui: Scheduler) : OldModel {

    private val subject = BehaviorSubject.create<CheckList>()
    private var checklistId: Long? = null

    override fun getCards(checklistId: Long): Observable<CheckList> {
        this.checklistId = checklistId
        notifyDatabaseChanged()
        return subject
    }

    override fun deleteItem(cardPosition: Int, itemPosition: Int) {
        subject
                .map { it.categories }
                .map { it.get(cardPosition) }
                .map { it.items.get(itemPosition) }
                .firstOrError()
                .map { dao.deleteItem(it) }
                .filter { it > 0 }
                .subscribeOn(io)
                .observeOn(ui)
                .subscribe({ notifyDatabaseChanged() }, {})
    }

    override fun getItem(cardPosition: Int, itemPosition: Int): Single<CheckListItem> {
        return subject
                .map { it.categories }
                .map { it.get(cardPosition) }
                .map { it.items.get(itemPosition) }
                .firstOrError()
                .subscribeOn(io)
                .observeOn(ui)
    }

    override fun onItemEdited(item: CheckListItem, cardPosition: Int, itemPosition: Int) {
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