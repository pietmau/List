package com.pppp.travelchecklist.navigation

import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.travelchecklist.menu.BetterMenuItem

interface MenuCreator {

    fun getItems(checkLists: List<RoomTravelCheckList>, string: String?): List<BetterMenuItem>
}