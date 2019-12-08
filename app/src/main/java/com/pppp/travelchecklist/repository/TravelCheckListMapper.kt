package com.pppp.travelchecklist.repository

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.entities.pokos.TravelCheckListProxy
import com.pppp.travelchecklist.createlist.presenter.Model

interface TravelCheckListMapper {

    fun map(list: List<Category>, model: Model): TravelCheckList

}

object TravelCheckListMapperImpl : TravelCheckListMapper {

    @Suppress("UNCHECKED_CAST")
    override fun map(list: List<Category>, model: Model): TravelCheckListImpl {
        val categories = list as List<CategoryImpl>
        val accomodation = model.accomodation?.title
        val weather = model.weather?.title
        val name = model.listName
        val duration = model.accomodation?.title
        val plannedActivities = model.plannedActivities.map { it.title }
        val travellers = model.travellers.map { it.title }
        return TravelCheckListImpl(
            categories = categories,
            travelCheckListProxy = TravelCheckListProxy(
                name = name,
                accomodation = accomodation,
                weather = weather,
                duration = duration,
                plannedActivities = plannedActivities,
                travellers = travellers,
                destination = model.destination?.name
            )
        )
    }
}