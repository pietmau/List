package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.main.model.OldModel
import com.pppp.travelchecklist.main.view.TravelListView
import com.pppp.travelchecklist.model.CheckList
import com.pppp.entities.CheckListItem
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


class OldMainPresenter(
    private val oldModel: OldModel,
    val worker: Scheduler,
    val ui: Scheduler) {

    var view: TravelListView? = null
    private var subscription: Disposable? = null

    fun unsubscribe() {
        if (subscription?.isDisposed == false) {
            subscription?.dispose()
        }
    }

    fun deleteChecklistItem(cardPosition: Int, itemPosition: Int) = oldModel.deleteItem(cardPosition, itemPosition)

    fun subscribe(view: TravelListView, observer: DisposableObserver<CheckList>) {
        this.view = view
        subscription = oldModel.getCards(1)
                .subscribeOn(worker)
                .observeOn(ui)
                .subscribeWith(observer)
    }

    fun getItem(cardPosition: Int, itemPosition: Int): Single<CheckListItem> {
        return oldModel.getItem(cardPosition, itemPosition)
    }

    fun onItemEdited(item: CheckListItem, cardPosition: Int, itemPosition: Int) {
        oldModel.onItemEdited(item, cardPosition, itemPosition)
    }
}