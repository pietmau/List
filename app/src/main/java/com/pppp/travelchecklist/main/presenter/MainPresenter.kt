package com.pppp.travelchecklist.main.presenter

import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.selector.model.Destination
import com.pppp.travelchecklist.selector.view.SelectorCallback


class MainPresenter : SelectorCallback {

    override fun onDestinationSelected(destination: Destination) {

    }

    override fun onAccomodationSelected(accomodation: TagImpl) {

    }

    override fun onPlannedActivitySelected(plannedActivity: TagImpl) {

    }

    override fun onPlannedActivityDeselected(plannedActivity: TagImpl) {

    }

    override fun onWhoisTravellingSelected(traveller: TagImpl) {

    }

    override fun onWhoisTravellingDeSelected(traveller: TagImpl) {

    }

    override fun onDurationSelected(duration: TagImpl) {

    }

    override fun onWeatherSelected(weather: TagImpl) {

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