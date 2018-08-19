package com.pppp.travelchecklist.selector.presenter

import android.arch.lifecycle.ViewModel
import com.pppp.travelchecklist.selector.model.Duration
import com.pppp.travelchecklist.selector.view.SelectorCallback
import com.pppp.travelchecklist.selector.model.Weather

class SelectorPresenter : ViewModel(), SelectorCallback {
    override fun onLengthSelected(duration: Duration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAccomodationSelected(accomodation: Accomodation) {

    }

    override fun onWeatherSelected(weather: Weather) {

    }

    override fun onFinishClicked() {

    }
}