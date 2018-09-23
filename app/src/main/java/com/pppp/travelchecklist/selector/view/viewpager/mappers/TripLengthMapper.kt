package com.pppp.travelchecklist.selector.view.viewpager.mappers

import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.model.Duration
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.utils.ResourcesWrapper

class TripLengthMapper(private val resources: ResourcesWrapper) :
    Mapper<Duration> {

    override fun map(item: ButtonsStrip.Item) =
        when {
            item.title.equals(
                resources.getString(R.string.yes),
                true
            ) -> Duration.LONG
            item.title.equals(
                resources.getString(R.string.no),
                true
            ) -> Duration.SHORT
            else -> throw UnsupportedOperationException("Can be either long or short")
        }

}
