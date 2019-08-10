package com.pppp.travelchecklist.main.view

import android.view.Menu
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.R
import javax.inject.Inject

class MenuCreatorImpl @Inject constructor() : MenuCreator {

    override fun initMenu(menu: Menu, userChecklists: List<TravelCheckList>) {
        val lists = menu.addSubMenu(R.string.your_lists)
        userChecklists.withIndex().forEach { (index, item) ->
            lists.add(Menu.NONE, index, Menu.NONE, item.name)
        }
    }

}