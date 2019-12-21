package com.pppp.travelchecklist.repository

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.Tag
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.RoomCategory
import com.pppp.entities.pokos.RoomCheckListItem
import com.pppp.entities.pokos.RoomCategoryProxy
import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.entities.pokos.RoomTravelCheckListProxy
import com.pppp.entities.pokos.RoomTag
import com.pppp.travelchecklist.createlist.presenter.Model

interface TravelCheckListMapper {

    fun map(list: List<Category>, model: Model): TravelCheckList

}

object TravelCheckListMapperImpl : TravelCheckListMapper {

    override fun map(list: List<Category>, model: Model): RoomTravelCheckList {
        val categories = list.map { category ->
            RoomCategoryProxy(title = category.title, description = category.description, items = mapItems(category))
        }
        val accomodation = model.accomodation?.title
        val weather = model.weather?.title
        val name = model.listName
        val duration = model.accomodation?.title
        val plannedActivities = model.plannedActivities.map { it.title }
        val travellers = model.travellers.map { it.title }
        TODO()
//        return RoomTravelCheckList(
//            categories = categories,
//            travelCheckListProxy = RoomTravelCheckListProxy(
//                name = name,
//                accomodation = accomodation,
//                weather = weather,
//                duration = duration,
//                plannedActivities = plannedActivities,
//                travellers = travellers,
//                destination = model.destination?.name
//            )
//        )
    }

    private fun mapItems(category: Category): List<RoomCheckListItem> = category.items
        .map { item ->
            val title = item.title
            val checked = item.checked
            val priority = item.priority
            val description = item.description
            val tags = mapTags(item.tags)
            RoomCheckListItem(title = title, checked = checked, priority = priority, description = description, tags = tags, optional = item.optional)
        }

    private fun mapTags(tags: List<Tag>): List<RoomTag> = tags.map { tag -> RoomTag(tag.title, tag.hidden) }
}