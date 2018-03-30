package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.main.model.Model
import com.pppp.travelchecklist.main.view.TravelListView
import com.pppp.travelchecklist.model.CardItemData
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


class MainPresenter(private val model: Model) {
    var view: TravelListView? = null
    private var subscription: Disposable? = null

    fun unsubscribe() {
        if (subscription?.isDisposed == false) {
            subscription?.dispose()
        }
    }

    fun onItemDeleteRequested(cardPosition: Int, itemPosition: Int) {
        model.onItemDeleteRequested(cardPosition, itemPosition)
    }

    fun subscribe(view: TravelListView, observer: DisposableObserver<List<CardItemData>>) {
        this.view = view
        subscription = model.getCards().subscribeWith(observer)
    }
}