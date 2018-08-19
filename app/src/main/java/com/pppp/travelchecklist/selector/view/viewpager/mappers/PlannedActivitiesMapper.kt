package com.pppp.travelchecklist.selector.view.viewpager.mappers

import com.pppp.travelchecklist.selector.model.PlannedActivity
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip

class PlannedActivitiesMapper:Mapper<PlannedActivity> {
    override fun map(item: ButtonsStrip.Item) = PlannedActivity(item.description)
}