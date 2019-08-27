package com.pppp.travelchecklist.main.view

import android.view.Menu
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.menu.BetterMenuItem

interface MenuCreator {

    fun getItems(checkLists: List<TravelCheckListImpl>, string: String?): List<BetterMenuItem>
}