package com.pppp.travelchecklist.selector.view

import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.selector.model.Destination

interface SelectorCallback {

    fun onFinishClicked()

    fun onAccomodationSelected(accomodation: TagImpl)

    fun onWeatherSelected(weather: TagImpl)

    fun onDurationSelected(duration: TagImpl)

    fun onPlannedActivitySelected(plannedActivity: TagImpl)

    fun onPlannedActivityDeselected(plannedActivity: TagImpl)

    fun onWhoisTravellingSelected(traveller: TagImpl)

    fun onWhoisTravellingDeSelected(traveller: TagImpl)

    fun onDestinationSelected(destination: Destination)

}