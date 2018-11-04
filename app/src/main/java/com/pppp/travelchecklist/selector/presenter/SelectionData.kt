package com.pppp.travelchecklist.selector.presenter

import android.os.Parcelable
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.selector.model.Destination
import kotlinx.android.parcel.Parcelize

@Parcelize
class SelectionData() : Parcelable {

    var accomodation: TagImpl? = null
    var weather: TagImpl? = null
    var duration: TagImpl? = null
    val plannedActivities = mutableListOf<TagImpl>()
    val travellers = mutableListOf<TagImpl>()
    var destination: Destination? = null

    fun onAccomodationSelected(accomodation: TagImpl) {
        this.accomodation = accomodation
    }

    fun onWeatherSelected(weather: TagImpl) {
        this.weather = weather
    }

    fun onDurationSelected(duration: TagImpl) {
        this.duration = duration
    }

    fun onPlannedActivitySelected(plannedActivity: TagImpl) {
        plannedActivities.add(plannedActivity)
    }

    fun onPlannedActivityDeselected(plannedActivity: TagImpl) {
        plannedActivities.remove(plannedActivity)
    }

    fun onWhoisTravellingSelected(traveller: TagImpl) {
        travellers.add(traveller)
    }

    fun onWhoisTravellingDeSelected(traveller: TagImpl) {
        travellers.remove(traveller)
    }

    fun onDestinationSelected(destination: Destination) {
        this.destination = destination
    }

    fun toList(): List<TagImpl> {
        var list = plannedActivities + travellers
        accomodation?.let {
            list += it
        }
        weather?.let {
            list += it
        }
        duration?.let {
            list += it
        }
        return list.toList()
    }

    val isEmpty: Boolean
        get() = accomodation == null && weather == null && duration == null && destination == null
                && plannedActivities.isEmpty() && travellers.isEmpty()

    companion object {
        val TAG = SelectionData::class.simpleName
    }
}