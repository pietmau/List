package com.pppp.travelchecklist.newlist.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import io.reactivex.subjects.PublishSubject

class NewListPresenter(
    private val selection: SelectionData,
    private val resourcesWrapper: ResourcesWrapper,
    private val listGenerator: ListGenerator
) : ViewModel(), NewListCallback {
    private var checkListId: String? = null
    private val subject = AsyncSubject.create<String>()
    private lateinit var subscription: Disposable
    val viewStates: LiveData<ViewState> = MutableLiveData()

    override fun onFinishClicked() {
        if (selection.isEmpty) {
            emit(ViewState.Error(resourcesWrapper.getString(R.string.must_make_selection)))
        } else {
            emit(ViewState.Progress)
            listGenerator.generate(selection)
                .toObservable()
                .subscribe(subject)
        }
    }

    fun subscribe() {
        subscription = subject
            .subscribeWith(object : SimpleDisposableObserver<String>() {
                override fun onNext(checkListId: String) {
                    onCheckListGenerated(checkListId)
                }

                override fun onError(e: Throwable) {
                    emit(ViewState.Error(e.localizedMessage))
                }
            })
    }

    private fun onCheckListGenerated(checkListId: String) {
        if (this@NewListPresenter.checkListId != null) {
            return
        }
        this@NewListPresenter.checkListId = checkListId
        emit(ViewState.ListGenerated(checkListId))
    }

    fun unsubscribe() {
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

    fun onSetupCompleted(text: String?) {
        listGenerator.setName(checkListId!!, text) // If it is null here, we better crash
        emit(ViewState.ListNamed(checkListId!!, text))
    }

    private fun emit(value: ViewState) {
        (viewStates as MutableLiveData).postValue(value)
    }

    sealed class ViewState {
        data class Error(val message: String) : ViewState()
        object Progress : ViewState()
        data class ListGenerated(val listId: String) : ViewState()
        data class ListNamed(val listId: String, val name: String?) : ViewState()
    }
}
