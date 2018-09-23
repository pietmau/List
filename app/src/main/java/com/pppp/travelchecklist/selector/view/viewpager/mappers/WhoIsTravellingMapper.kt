package com.pppp.travelchecklist.selector.view.viewpager.mappers

import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.model.Traveller
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.utils.ResourcesWrapper

class WhoIsTravellingMapper(private val wrapper: ResourcesWrapper) : Mapper<Traveller> {

    override fun map(item: ButtonsStrip.Item) = when {
        item.title.equals(wrapper.getString(R.string.babies)) -> Traveller.BABIES
        item.title.equals(wrapper.getString(R.string.male)) -> Traveller.MALE
        item.title.equals(wrapper.getString(R.string.fermale)) -> Traveller.FERMALE
        item.title.equals(wrapper.getString(R.string.kids_toddlers)) -> Traveller.KIDS_TODDLERS
        else -> throw UnsupportedOperationException()
    }
}