package com.pppp.travelchecklist.selector.view

import com.pppp.travelchecklist.selector.model.Accomodation
import com.pppp.travelchecklist.selector.model.PlannedActivity
import com.pppp.travelchecklist.selector.model.Weather
import com.pppp.travelchecklist.selector.model.Duration

interface SelectorCallback {

    fun onFinishClicked()

    fun onAccomodationSelected(accomodation: Accomodation)

    fun onWeatherSelected(weather: Weather)

    fun onLengthSelected(duration: Duration)

    fun onPlannedActivitySelected(map: PlannedActivity)

    fun onPlannedActivityDeselected(map: PlannedActivity)

}