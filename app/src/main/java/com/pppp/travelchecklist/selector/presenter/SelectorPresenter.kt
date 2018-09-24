package com.pppp.travelchecklist.selector.presenter

import android.arch.lifecycle.ViewModel
import com.pppp.entities.CheckList
import com.pppp.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.selector.model.Accomodation
import com.pppp.travelchecklist.selector.model.Destination
import com.pppp.travelchecklist.selector.model.Duration
import com.pppp.travelchecklist.selector.model.Weather
import com.pppp.travelchecklist.selector.view.ISelectorView
import com.pppp.travelchecklist.selector.view.SelectorCallback
import com.pppp.travelchecklist.utils.ResourcesWrapper
import com.pppp.travelchecklist.utils.SimpleDisposableObserver
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

class SelectorPresenter(
    private val selection: SelectionData,
    private val resourcesWrapper: ResourcesWrapper,
    private val listGenerator: ListGenerator,
    private val mainThread: Scheduler,
    private val workerThread: Scheduler
) : ViewModel(), SelectorCallback {
    private var view: ISelectorView? = null
    private val subject = BehaviorSubject.create<CheckList>()
    private lateinit var subscription: Disposable

    override fun onFinishClicked() {
        if (selection.isEmpty) {
            view?.onError(resourcesWrapper.getString(R.string.must_make_selection))
        } else {
            listGenerator.generate(selection)
                .subscribeOn(workerThread)
                .doOnNext { subject.onNext(it) }
                .subscribe({}, {})
        }
    }

    fun subscribe(view: ISelectorView) {
        this.view = view
        subscription = subject
            .observeOn(mainThread)
            .subscribeWith(object : SimpleDisposableObserver<CheckList>() {
                override fun onNext(t: CheckList) {

                }
            })
    }

    fun unsubscribe() {
        view = null
        subscription.dispose()
    }

    override fun onAccomodationSelected(accomodation: Accomodation) {
        selection.onAccomodationSelected(accomodation)
    }

    override fun onWeatherSelected(weather: Weather) {
        selection.onWeatherSelected(weather)
    }

    override fun onDurationSelected(duration: Duration) {
        selection.onDurationSelected(duration)
    }

    override fun onPlannedActivitySelected(plannedActivity: Tag?) {
        //selection.onPlannedActivitySelected(plannedActivity)
    }

    override fun onPlannedActivityDeselected(plannedActivity: Tag?) {
        selection.onPlannedActivityDeselected(plannedActivity)
    }

    override fun onWhoisTravellingSelected(traveller: Tag?) {
        //selection.onTagSelected(traveller)
    }

    override fun onWhoisTravellingDeSelected(tag: Tag?) {
        selection.onWhoisTravellingDeSelected(tag)
    }

    override fun onDestinationSelected(destination: Destination) {
        selection.onDestinationSelected(destination)
    }
}