package com.pppp.travelchecklist.main.view

import android.view.Menu
import com.pietrantuono.entities.TravelCheckList

interface MenuCreator {
    fun initMenu(menu: Menu, userChecklists: List<TravelCheckList>)
}