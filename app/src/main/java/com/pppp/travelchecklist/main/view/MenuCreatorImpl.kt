package com.pppp.travelchecklist.main.view

import android.view.Menu
import com.google.android.material.internal.NavigationMenu
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.viewmodel.TitleUseCase
import javax.inject.Inject

class MenuCreatorImpl @Inject constructor(private val titleUseCase: TitleUseCase) : MenuCreator {

    override fun initMenu(menu: Menu, userChecklists: List<TravelCheckList>, lastVisited: String?) {
        val submenu = menu.addSubMenu(R.string.your_lists)
        userChecklists.withIndex().forEach { (index, item) ->
            val menuItem = submenu.add(Menu.NONE, index, Menu.NONE, titleUseCase.getTitleForMenu(item))
            if (item.id?.equals(lastVisited) == true) {
                menuItem.setChecked(true)
            }
        }
    }

}