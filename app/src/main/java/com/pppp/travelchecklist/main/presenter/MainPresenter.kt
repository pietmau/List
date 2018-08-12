package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.selector.view.SelectorCallback
import com.pppp.travelchecklist.selector.view.model.Accomodation
import com.pppp.travelchecklist.selector.view.model.Weather


class MainPresenter : SelectorCallback {
    override fun onAccomadationSelected(accomodation: Accomodation) {

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