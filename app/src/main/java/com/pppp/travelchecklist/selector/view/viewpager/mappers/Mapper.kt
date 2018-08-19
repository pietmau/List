package com.pppp.travelchecklist.selector.view.viewpager.mappers

import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip

interface Mapper<O> {
    fun map(item: ButtonsStrip.Item): O
}
