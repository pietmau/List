package com.pppp.travelchecklist.selector.view

import com.pppp.entities.Tag
import com.pppp.travelchecklist.selector.model.*

interface SelectorCallback {

    fun onFinishClicked()

    fun onAccomodationSelected(accomodation: Accomodation)

    fun onWeatherSelected(weather: Weather)

    fun onDurationSelected(duration: Duration)

    fun onPlannedActivitySelected(plannedActivity: Tag?)

    fun onPlannedActivityDeselected(plannedActivity: Tag?)

    fun onWhoisTravellingSelected(traveller: Tag?)

    fun onWhoisTravellingDeSelected(traveller: Tag?)

    fun onDestinationSelected(destination: Destination)

}