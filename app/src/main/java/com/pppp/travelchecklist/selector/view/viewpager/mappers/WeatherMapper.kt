package com.pppp.travelchecklist.selector.view.viewpager.mappers

import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.model.Weather
import com.pppp.travelchecklist.utils.ResourcesWrapper

class WeatherMapper(private val wrapper: ResourcesWrapper) : Mapper<Weather> {

    override fun map(item: ButtonsStrip.Item) = when {
        item.description.equals(wrapper.getString(R.string.hot)) -> Weather.WARM
        item.description.equals(wrapper.getString(R.string.cold)) -> Weather.COLD
        else -> throw UnsupportedOperationException("Can be either cold or warm")
    }
}