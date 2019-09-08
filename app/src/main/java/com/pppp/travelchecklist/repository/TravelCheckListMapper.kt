package com.pppp.travelchecklist.repository

import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.newlist.presenter.Model

interface TravelCheckListMapper {

    fun map(model: Model): TravelCheckList

}

object TravelCheckListMapperImpl : TravelCheckListMapper {

    override fun map(model: Model): TravelCheckListImpl {
        val accomodation = model.accomodation?.title
        val weather = model.weather?.title
        val name = model.listName
        val duration = model.accomodation?.title
        val plannedActivities = model.plannedActivities.map { it.title }
        val travellers = model.travellers.map { it.title }
        return TravelCheckListImpl(
            categories = emptyList(),
            name = name,
            accomodation = accomodation,
            weather = weather,
            duration = duration,
            plannedActivities = plannedActivities,
            travellers = travellers,
            destination = model.destination?.name
        )
    }
}