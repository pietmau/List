package com.pppp.travelchecklist.main.presenter

import com.pppp.entities.Tag
import com.pppp.travelchecklist.selector.model.*
import com.pppp.travelchecklist.selector.view.SelectorCallback


class MainPresenter : SelectorCallback {

    override fun onDestinationSelected(destination: Destination) {

    }

    override fun onAccomodationSelected(accomodation: Accomodation) {

    }

    override fun onPlannedActivitySelected(plannedActivity: Tag?) {

    }

    override fun onPlannedActivityDeselected(plannedActivity: Tag?) {

    }

    override fun onWhoisTravellingSelected(traveller: Tag?) {

    }

    override fun onWhoisTravellingDeSelected(traveller: Tag?) {

    }

    override fun onDurationSelected(duration: Duration) {

    }

    override fun onWeatherSelected(weather: Weather) {

    }

    private var mainActivity: MainView? = null

    override fun onFinishClicked() {
    }

    fun bind(mainActivity: MainView) {
        this.mainActivity = mainActivity
    }

    fun unbind() {
        mainActivity = null
    }
}