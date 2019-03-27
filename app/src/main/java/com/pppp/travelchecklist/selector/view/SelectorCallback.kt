package com.pppp.travelchecklist.selector.view

import com.pppp.entities.pokos.Tag
import com.pppp.travelchecklist.selector.model.Destination

interface SelectorCallback {

    fun onFinishClicked()

    fun onAccomodationSelected(accomodation: Tag)

    fun onWeatherSelected(weather: Tag)

    fun onDurationSelected(duration: Tag)

    fun onPlannedActivitySelected(plannedActivity: Tag)

    fun onPlannedActivityDeselected(plannedActivity: Tag)

    fun onWhoisTravellingSelected(traveller: Tag)

    fun onWhoisTravellingDeSelected(traveller: Tag)

    fun onDestinationSelected(destination: Destination)

}