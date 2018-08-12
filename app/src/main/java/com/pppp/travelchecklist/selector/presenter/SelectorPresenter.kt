package com.pppp.travelchecklist.selector.presenter

import android.arch.lifecycle.ViewModel
import com.pppp.travelchecklist.selector.view.SelectorCallback
import com.pppp.travelchecklist.selector.view.model.Accomodation
import com.pppp.travelchecklist.selector.view.model.Weather

class SelectorPresenter : ViewModel(), SelectorCallback {
    override fun onAccomadationSelected(accomodation: Accomodation) {

    }

    override fun onWeatherSelected(weather: Weather) {

    }

    override fun onFinishClicked() {

    }
}