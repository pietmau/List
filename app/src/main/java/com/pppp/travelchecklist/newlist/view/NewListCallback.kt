package com.pppp.travelchecklist.newlist.view

import com.pietrantuono.entities.Tag
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.newlist.model.Destination

interface NewListCallback {

    fun onFinishClicked()

    fun onAccommodationSelected(accomodation: Tag)

    fun onWeatherSelected(weather: Tag)

    fun onDurationSelected(duration: Tag)

    fun onPlannedActivitySelected(plannedActivity: Tag)

    fun onPlannedActivityDeselected(plannedActivity: Tag)

    fun onWhoisTravellingSelected(traveller: Tag)

    fun onWhoisTravellingDeSelected(traveller: Tag)

    fun onDestinationSelected(destination: Destination)

    fun onNameChanged(name: String)

    fun navigateBack(): Boolean

    fun onPageChanged(position: Int)

    fun onDurationDeselected(item: Tag)

    fun onAccommodationDeselected(item: Tag)

    fun onWeatherDeselected(item: Tag)
}