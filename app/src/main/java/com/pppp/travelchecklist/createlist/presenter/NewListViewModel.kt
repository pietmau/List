package com.pppp.travelchecklist.createlist.presenter

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.ViewState
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.createlist.model.Destination
import com.pppp.travelchecklist.utils.ResourcesWrapper

class NewListViewModel(
    private val model: Model,
    private val resourcesWrapper: ResourcesWrapper,
    override val states: LiveData<NewListViewState> = MutableLiveData(),
    var transientStates: ((TransientState) -> Unit)? = null
) : ViewModel(), ViewStatesProducer<NewListViewModel.NewListViewState> {

    @SuppressLint("DefaultLocale")
    fun onNameChanged(name: String) {
        model.listName = name.capitalize()
        updateUi()
    }

    @SuppressLint("CheckResult")
    fun onFinishClicked() {
        if (model.isDataComplete()) {
            updateUi(true)
            model.generate()
                .subscribe({
                    this@NewListViewModel.model.checkListId = it
                    updateUi(false, it)
                }, {
                    transientStates?.invoke(TransientState(TransientState.Error.GenericError(it.localizedMessage)))
                })
        } else {
            emitErrors()
        }
    }

    private fun emitErrors() {
        val genericError = if (model.isEmpty) TransientState.Error.GenericError(resourcesWrapper.getString(R.string.must_make_selection)) else null
        val noNameError = if (!model.hasValidName()) TransientState.Error.NoNameError else null
        transientStates?.invoke(TransientState(genericError, noNameError))
    }

    fun onAccommodationSelected(accomodation: Tag) {
        model.onAccomodationSelected(accomodation)
        updateUi()
    }

    fun onWeatherSelected(weather: Tag) {
        model.onWeatherSelected(weather)
        updateUi()
    }

    fun onDurationSelected(duration: Tag) {
        model.onDurationSelected(duration)
        updateUi()
    }

    fun onPlannedActivitySelected(plannedActivity: Tag) {
        model.onPlannedActivitySelected(plannedActivity)
        updateUi()
    }

    fun onPlannedActivityDeselected(plannedActivity: Tag) {
        model.onPlannedActivityDeselected(plannedActivity)
        updateUi()
    }

    fun onWhoisTravellingSelected(traveller: Tag) {
        model.onWhoisTravellingSelected(traveller)
        updateUi()
    }

    fun onWhoisTravellingDeSelected(tag: Tag) {
        model.onWhoisTravellingDeSelected(tag)
        updateUi()
    }

    fun onDestinationSelected(destination: Destination) {
        model.onDestinationSelected(destination)
        updateUi()
    }

    fun onDurationDeselected(item: Tag) {
        model.onDurationDeselected(item)
        updateUi()
    }

    fun onAccommodationDeselected(item: Tag) {
        model.onAccommodationDeselected(item)
        updateUi()
    }

    fun onWeatherDeselected(item: Tag) {
        model.onWeatherDeselected(item)
        updateUi()
    }

    private fun updateUi(progress: Boolean = false, listId: String? = null) {
        val enableFinish = model.isDataComplete()
        (states as MutableLiveData).postValue(NewListViewState(progress, enableFinish, listId))
    }

    fun onPageChanged() {
        updateUi()
    }

    data class NewListViewState(val showProgress: Boolean, val enableFinish: Boolean, val listId: String?) : ViewState

    data class TransientState(val genericError: Error.GenericError? = null, val noName: Error.NoNameError? = null) {
        sealed class Error {
            data class GenericError(val message: String) : Error()
            object NoNameError : Error()
        }
    }
}
