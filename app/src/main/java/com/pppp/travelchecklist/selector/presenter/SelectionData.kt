package com.pppp.travelchecklist.selector.presenter

import android.os.Parcelable
import com.pppp.travelchecklist.selector.model.*
import kotlinx.android.parcel.Parcelize

@Parcelize
class SelectionData() : Parcelable {
    var accomodation: Accomodation? = null
    var weather: Weather? = null
    var duration: Duration? = null
    val plannedActivities = mutableListOf<PlannedActivity>()
    val travellers = mutableListOf<Traveller>()

    fun onAccomodationSelected(accomodation: Accomodation) {
        this.accomodation = accomodation
    }

    fun onWeatherSelected(weather: Weather) {
        this.weather = weather
    }

    fun onDurationSelected(duration: Duration) {
        this.duration = duration
    }

    fun onPlannedActivitySelected(plannedActivity: PlannedActivity) {
        plannedActivities.add(plannedActivity)
    }

    fun onPlannedActivityDeselected(plannedActivity: PlannedActivity) {
        plannedActivities.remove(plannedActivity)
    }

    fun onWhoisTravellingSelected(traveller: Traveller) {
        travellers.add(traveller)
    }

    fun onWhoisTravellingDeSelected(traveller: Traveller) {
        travellers.remove(traveller)
    }

    val isEmpty: Boolean
        get() =
            accomodation == null && weather == null && duration == null && plannedActivities.isEmpty() && travellers.isEmpty()

}