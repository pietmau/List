package com.pppp.travelchecklist.selector.presenter

import android.os.Parcelable
import com.pppp.entities.Tag
import com.pppp.travelchecklist.selector.model.Accomodation
import com.pppp.travelchecklist.selector.model.Destination
import com.pppp.travelchecklist.selector.model.Duration
import com.pppp.travelchecklist.selector.model.Weather
import kotlinx.android.parcel.Parcelize

@Parcelize
class SelectionData() : Parcelable {

    var accomodation: Accomodation? = null
    var weather: Weather? = null
    var duration: Duration? = null
    val plannedActivities = mutableListOf<Tag>()
    val travellers = mutableListOf<Tag>()
    var destination: Destination? = null

    fun onAccomodationSelected(accomodation: Accomodation) {
        this.accomodation = accomodation
    }

    fun onWeatherSelected(weather: Weather) {
        this.weather = weather
    }

    fun onDurationSelected(duration: Duration) {
        this.duration = duration
    }

    fun onPlannedActivitySelected(plannedActivity: Tag) {
        plannedActivities.add(plannedActivity)
    }

    fun onPlannedActivityDeselected(plannedActivity: Tag?) {
        plannedActivities.remove(plannedActivity)
    }

    fun onWhoisTravellingSelected(traveller: Tag) {
        travellers.add(traveller)
    }

    fun onWhoisTravellingDeSelected(traveller: Tag?) {
        travellers.remove(traveller)
    }

    fun onDestinationSelected(destination: Destination) {
        this.destination = destination
    }

    val isEmpty: Boolean
        get() =
            accomodation == null && weather == null && duration == null && destination == null
                    && plannedActivities.isEmpty() && travellers.isEmpty()

    companion object {
        val TAG = SelectionData::class.simpleName
    }
}