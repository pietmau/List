package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.selector.view.SelectorCallback
import com.pppp.travelchecklist.selector.model.Weather
import com.pppp.travelchecklist.selector.model.Duration


class MainPresenter : SelectorCallback {
    override fun onLengthSelected(duration: Duration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAccomodationSelected(accomodation: Accomodation) {

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