package com.pppp.travelchecklist.selector.view.viewpager.mappers

import com.pppp.travelchecklist.selector.model.Accomodation
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip


class AccomodationMapper : Mapper<Accomodation> {

    override fun map(item: ButtonsStrip.Item) = Accomodation(item.title)
}