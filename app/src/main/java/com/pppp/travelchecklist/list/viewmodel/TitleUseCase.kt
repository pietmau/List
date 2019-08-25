package com.pppp.travelchecklist.list.viewmodel

import com.pietrantuono.entities.TravelCheckList
import java.lang.StringBuilder

interface TitleUseCase {

    fun getTitle(travelCheckList: TravelCheckList): String

    fun getSubTitle(travelCheckList: TravelCheckList): String

    fun getTitleForMenu(travelCheckList: TravelCheckList): String
}

object TitleUseCaseImpl : TitleUseCase {

    override fun getSubTitle(travelCheckList: TravelCheckList): String {
        val travelInfo = mutableListOf<String>()
        travelCheckList.destination?.let {
            travelInfo.add(it)
        }
        travelCheckList.accomodation?.let {
            travelInfo.add(it)
        }
        travelInfo.addAll(travelCheckList.travellers)
        travelInfo.addAll(travelCheckList.plannedActivities)
        return travelInfo.joinToString()
    }

    override fun getTitle(travelCheckList: TravelCheckList) = travelCheckList.name ?: ""

    override fun getTitleForMenu(travelCheckList: TravelCheckList): String {
        if (getSubTitle(travelCheckList).isNullOrBlank()) {
            return travelCheckList.name ?: ""
        }
        return travelCheckList.name + " (" + getSubTitle(travelCheckList) + " )"
    }
}
