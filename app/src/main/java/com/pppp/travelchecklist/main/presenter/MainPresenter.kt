package com.pppp.travelchecklist.main.presenter

import android.arch.lifecycle.ViewModel
import com.pppp.travelchecklist.main.model.Model
import com.pppp.travelchecklist.main.view.TravelListView
import com.pppp.travelchecklist.model.SimpleObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.BehaviorSubject


class MainPresenter(private val model: Model) : ViewModel {
    private var subscription: DisposableObserver<TravelListView.ViewConfiguration>? = null

    private val viewConfigurations = BehaviorSubject.create<TravelListView.ViewConfiguration>()

    val commandsFromView = object : SimpleObserver<TravelListView.Action>() {
        override fun onNext(action: TravelListView.Action) {
            processAction(action)
        }
    }

    private fun processAction(action: TravelListView.Action) {}

    fun subscribe(observer: DisposableObserver<TravelListView.ViewConfiguration>) {
        subscription = viewConfigurations.subscribeWith(observer)
        if (viewConfigurations.values == null || viewConfigurations.values.isEmpty()) {
            onStart()
        }
    }

    private fun onStart() {
        model.getCards()
                .map { TravelListView.ViewConfiguration(TravelListView.ViewConfiguration.Status.START, it) }
                .subscribeWith(viewConfigurations)
    }

    fun unsubscribe() {
        if (subscription?.isDisposed == false) {
            subscription?.dispose()
        }
    }

}