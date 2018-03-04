package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.main.model.Model
import com.pppp.travelchecklist.main.view.TravelListView
import io.reactivex.observers.DisposableObserver


class MainPresenter(private val model: Model) {
    var view: TravelListView? = null
    private var subscription: DisposableObserver<TravelListView.ViewStatus>? = null

    fun subscribe(observer: DisposableObserver<TravelListView.ViewStatus>) {//TODO unsubscribe
        subscription = model.getCards()
                .map { TravelListView.ViewStatus(TravelListView.ViewStatus.Status.NONE, it) }
                .subscribeWith(observer)
    }

    fun unsubscribe() {
        if (subscription?.isDisposed == false) {
            subscription?.dispose()
        }
    }
}