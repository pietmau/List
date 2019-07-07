package com.pppp.travelchecklist.selector.presenter

import android.arch.lifecycle.ViewModel
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckList
import com.pietrantuono.entities.Tag
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.selector.model.Destination
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
    private val subject = BehaviorSubject.create<List<Category>>()
    private lateinit var subscription: Disposable

    override fun onFinishClicked() {
        if (selection.isEmpty) {
            view?.onError(resourcesWrapper.getString(R.string.must_make_selection))
        } else {
            listGenerator.generate(selection)
                .subscribeOn(workerThread)
                .observeOn(mainThread)
                .toObservable()
                .subscribe(subject)
        }
    }

    fun subscribe(view: ISelectorView) {
        this.view = view
        subscription = subject
            .observeOn(mainThread)
            .subscribeWith(object : SimpleDisposableObserver<List<Category>>() {
                override fun onNext(t: List<Category>) {

                }
            })
    }

    fun unsubscribe() {
        view = null
        //subscription.dispose()
    }

    override fun onAccomodationSelected(accomodation: Tag) {
        selection.onAccomodationSelected(accomodation)
    }

    override fun onWeatherSelected(weather: Tag) {
        selection.onWeatherSelected(weather)
    }

    override fun onDurationSelected(duration: Tag) {
        selection.onDurationSelected(duration)
    }

    override fun onPlannedActivitySelected(plannedActivity: Tag) {
        selection.onPlannedActivitySelected(plannedActivity)
    }

    override fun onPlannedActivityDeselected(plannedActivity: Tag) {
        selection.onPlannedActivityDeselected(plannedActivity)
    }

    override fun onWhoisTravellingSelected(traveller: Tag) {
        selection.onWhoisTravellingSelected(traveller)
    }

    override fun onWhoisTravellingDeSelected(tag: Tag) {
        selection.onWhoisTravellingDeSelected(tag)
    }

    override fun onDestinationSelected(destination: Destination) {
        selection.onDestinationSelected(destination)
    }
}