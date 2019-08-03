package com.pppp.travelchecklist.newlist.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.newlist.model.Destination
import com.pppp.travelchecklist.utils.ResourcesWrapper
import com.pppp.travelchecklist.utils.SimpleDisposableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.AsyncSubject

class NewListPresenter(
    private val selection: SelectionData,
    private val resourcesWrapper: ResourcesWrapper,
    private val listGenerator: ListGenerator
) : ViewModel() {
    private var checkListId: String? = null
    private val subject = AsyncSubject.create<String>()
    private lateinit var subscription: Disposable
    val viewStates: LiveData<ViewState> = MutableLiveData()
    private var listName: String? = null

    fun setChecklistName(name: String) {
        this.listName = name
    }

    fun onFinishClicked() {
        if (selection.isEmpty || listName.isNullOrBlank()) {
            emitErrors()
        } else {
            emit(ViewState.Progress)
            listGenerator.generate(selection, listName!!)
                .toObservable()
                .subscribe(subject)
        }
    }

    private fun emitErrors() {
        val incompleteDataMessage = if (selection.isEmpty) resourcesWrapper.getString(R.string.must_make_selection) else null
        val noNameMessage = if (listName.isNullOrBlank()) resourcesWrapper.getString(R.string.please_input_name) else null
        emit(ViewState.Error(incompleteDataMessage, noNameMessage))
    }

    fun subscribe() {
        subscription = subject
            .subscribeWith(object : SimpleDisposableObserver<String>() {
                override fun onNext(checkListId: String) {
                    if (this@NewListPresenter.checkListId != null) {
                        return
                    }
                    this@NewListPresenter.checkListId = checkListId
                    emit(ViewState.ListGenerated(checkListId))
                }

                override fun onError(e: Throwable) {
                    emit(ViewState.Error(e.localizedMessage, null))
                }
            })
    }

    fun unsubscribe() {
        subscription.dispose()
    }

    fun onAccommodationSelected(accomodation: Tag) {
        selection.onAccomodationSelected(accomodation)
    }

    fun onWeatherSelected(weather: Tag) {
        selection.onWeatherSelected(weather)
    }

    fun onDurationSelected(duration: Tag) {
        selection.onDurationSelected(duration)
    }

    fun onPlannedActivitySelected(plannedActivity: Tag) {
        selection.onPlannedActivitySelected(plannedActivity)
    }

    fun onPlannedActivityDeselected(plannedActivity: Tag) {
        selection.onPlannedActivityDeselected(plannedActivity)
    }

    fun onWhoisTravellingSelected(traveller: Tag) {
        selection.onWhoisTravellingSelected(traveller)
    }

    fun onWhoisTravellingDeSelected(tag: Tag) {
        selection.onWhoisTravellingDeSelected(tag)
    }

    fun onDestinationSelected(destination: Destination) {
        selection.onDestinationSelected(destination)
    }

    private fun emit(value: ViewState) {
        (viewStates as MutableLiveData).postValue(value)
    }

    sealed class ViewState {
        data class Error(val incompleteDataMessage: String?, val noNameMessage: String?) : ViewState()
        object Progress : ViewState()
        data class ListGenerated(val listId: String) : ViewState()
    }
}
