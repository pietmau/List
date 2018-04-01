package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.main.model.Model
import com.pppp.travelchecklist.main.view.TravelListView
import com.pppp.travelchecklist.model.CheckList
import com.pppp.travelchecklist.model.CheckListItemData
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


class MainPresenter(
        private val model: Model,
        val worker: Scheduler,
        val ui: Scheduler) {

    var view: TravelListView? = null
    private var subscription: Disposable? = null

    fun unsubscribe() {
        if (subscription?.isDisposed == false) {
            subscription?.dispose()
        }
    }

    fun deleteChecklistItem(cardPosition: Int, itemPosition: Int) = model.deleteItem(cardPosition, itemPosition)

    fun subscribe(view: TravelListView, observer: DisposableObserver<CheckList>) {
        this.view = view
        subscription = model.getCards(1)
                .subscribeOn(worker)
                .observeOn(ui)
                .subscribeWith(observer)
    }

    fun getItem(cardPosition: Int, itemPosition: Int): CheckListItemData {
        model.getItem(cardPosition, itemPosition)
        TODO()
    }

    fun onItemEdited(item: CheckListItemData, cardPosition: Int, itemPosition: Int) {
        model.onItemEdited(item, cardPosition, itemPosition)
    }
}