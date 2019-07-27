package com.pppp.travelchecklist.newlist.presenter

import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.newlist.model.Destination
import com.pppp.travelchecklist.newlist.view.NewListView
import com.pppp.travelchecklist.newlist.view.NewListCallback
import com.pppp.travelchecklist.utils.ResourcesWrapper
import com.pppp.travelchecklist.utils.SimpleDisposableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.AsyncSubject

class NewListPresenter(
    private val selection: SelectionData,
    private val resourcesWrapper: ResourcesWrapper,
    private val listGenerator: ListGenerator
) : ViewModel(), NewListCallback {

    private var view: NewListView? = null
    private val subject = AsyncSubject.create<String>()
    private lateinit var subscription: Disposable

    override fun onFinishClicked() {
        if (selection.isEmpty) {
            view?.onError(resourcesWrapper.getString(R.string.must_make_selection))
        } else {
            view?.generatingList()
            listGenerator.generate(selection)
                .toObservable()
                .subscribe(subject)
        }
    }

    fun subscribe(view: NewListView) {
        this.view = view
        subscription = subject
            .subscribeWith(object : SimpleDisposableObserver<String>() {
                override fun onNext(checkListId: String) {
                    this@NewListPresenter.view?.onListGenerated(checkListId)
                }

                override fun onError(e: Throwable) {
                    this@NewListPresenter.view?.onError(e.localizedMessage)
                }
            })
    }

    fun unsubscribe() {
        view = null
        subscription.dispose()
    }

    override fun onAccommodationSelected(accomodation: Tag) {
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