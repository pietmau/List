package com.pppp.travelchecklist.selector.view

import com.pppp.entities.Tag
import com.pppp.travelchecklist.selector.model.*

interface SelectorCallback {

    fun onFinishClicked()

    fun onAccomodationSelected(accomodation: Accomodation)

    fun onWeatherSelected(weather: Weather)

    fun onDurationSelected(duration: Duration)

    fun onPlannedActivitySelected(plannedActivity: PlannedActivity)

    fun onPlannedActivityDeselected(plannedActivity: PlannedActivity)

    fun onWhoisTravellingSelected(traveller: Tag?)

    fun onWhoisTravellingDeSelected(traveller: Traveller)

    fun onDestinationSelected(destination: Destination)

}