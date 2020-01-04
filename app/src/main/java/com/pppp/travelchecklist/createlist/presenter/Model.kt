package com.pppp.travelchecklist.createlist.presenter

import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.createlist.model.Destination

class Model(
    private val listGenerator: ListGenerator
) {
    var accomodation: Tag? = null
    var weather: Tag? = null
    var duration: Tag? = null
    val plannedActivities = mutableListOf<Tag>()
    val travellers = mutableListOf<Tag>()
    var destination: Destination? = null
    var listName: String? = null
    var checkListId: String? = null

    fun hasValidName() = !listName.isNullOrBlank()

    fun onAccomodationSelected(accomodation: Tag) {
        this.accomodation = accomodation
    }

    fun onWeatherSelected(weather: Tag) {
        this.weather = weather
    }

    fun onDurationSelected(duration: Tag) {
        this.duration = duration
    }

    fun onPlannedActivitySelected(plannedActivity: Tag) {
        plannedActivities.add(plannedActivity)
    }

    fun onPlannedActivityDeselected(plannedActivity: Tag) {
        plannedActivities.remove(plannedActivity)
    }

    fun onWhoisTravellingSelected(traveller: Tag) {
        travellers.add(traveller)
    }

    fun onWhoisTravellingDeSelected(traveller: Tag) {
        travellers.remove(traveller)
    }

    fun onDestinationSelected(destination: Destination) {
        this.destination = destination
    }

    fun onDurationDeselected(item: Tag) {
        duration = null
    }

    fun onAccommodationDeselected(item: Tag) {
        accomodation = null
    }

    fun onWeatherDeselected(item: Tag) {
        weather = null
    }

    fun toList(): List<Tag> {
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

    fun isDataComplete() = ((!isEmpty) && hasValidName())

    fun generate() = listGenerator.generate(this, requireNotNull(listName))

    val isEmpty: Boolean
        get() = accomodation == null && weather == null && duration == null && destination == null
            && plannedActivities.isEmpty() && travellers.isEmpty()

}