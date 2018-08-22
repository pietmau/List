package com.pppp.travelchecklist.selector.presenter

import android.arch.lifecycle.ViewModel
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.model.*
import com.pppp.travelchecklist.selector.view.ISelectorView
import com.pppp.travelchecklist.selector.view.SelectorCallback
import com.pppp.travelchecklist.utils.ResourcesWrapper

class SelectorPresenter(
    private val selection: SelectionData,
    private val resourcesWrapper: ResourcesWrapper
) : ViewModel(), SelectorCallback {
    lateinit var view: ISelectorView

    override fun onFinishClicked() {
        if (selection.isEmpty) {
            view.onError(resourcesWrapper.getString(R.string.must_make_selection))
        } else {
            view.generateAndViewList(selection)
        }
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

    override fun onPlannedActivitySelected(plannedActivity: PlannedActivity) {
        selection.onPlannedActivitySelected(plannedActivity)
    }

    override fun onPlannedActivityDeselected(plannedActivity: PlannedActivity) {
        selection.onPlannedActivityDeselected(plannedActivity)
    }

    override fun onWhoisTravellingSelected(traveller: Traveller) {
        selection.onWhoisTravellingSelected(traveller)
    }

    override fun onWhoisTravellingDeSelected(traveller: Traveller) {
        selection.onWhoisTravellingDeSelected(traveller)
    }

}