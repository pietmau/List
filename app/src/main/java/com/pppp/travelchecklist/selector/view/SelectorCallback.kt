package com.pppp.travelchecklist.selector.view

import com.pppp.travelchecklist.selector.view.model.Accomodation
import com.pppp.travelchecklist.selector.view.model.Weather

interface SelectorCallback {

    fun onFinishClicked()

    fun onAccomadationSelected(accomodation: Accomodation)

    fun onWeatherSelected(weather: Weather)
}