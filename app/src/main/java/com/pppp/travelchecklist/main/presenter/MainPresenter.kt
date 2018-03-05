package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.main.model.Reducer
import com.pppp.travelchecklist.main.view.TravelListView
import com.pppp.travelchecklist.model.SimpleObserver
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject


class MainPresenter(
        private val reducer: Reducer
) {

    private val viewConfigurations = BehaviorSubject.create<TravelListView.ViewConfiguration>()

    fun observe() = viewConfigurations
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { emitStartIfneeded() }

    private fun emitStartIfneeded() {
        if (viewConfigurations.values == null || viewConfigurations.values.isEmpty()) {
            reduce(TravelListView.Action.StartRequest())
        }
    }

    private fun reduce(request: TravelListView.Action) {
        viewConfigurations.onNext(reducer.reduce(request))
    }

    fun subscribe(observable: Observable<TravelListView.Action>): DisposableObserver<TravelListView.Action> {
        return observable.observeOn(Schedulers.io()).subscribeWith(object : SimpleObserver<TravelListView.Action>() {
            override fun onNext(action: TravelListView.Action) {
                reduce(action)
            }
        })
    }
}