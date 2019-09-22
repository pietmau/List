package com.pppp.travelchecklist.main.view

import android.view.Menu
import com.google.android.material.internal.NavigationMenu
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.viewmodel.TitleUseCase
import com.pppp.travelchecklist.menu.BetterMenuItem
import java.lang.IllegalArgumentException
import java.security.InvalidParameterException
import javax.inject.Inject

class MenuCreatorImpl @Inject constructor(private val titleUseCase: TitleUseCase) : MenuCreator {

    override fun getItems(checkLists: List<TravelCheckListImpl>, lastVisited: String?) =
        checkLists.map {
            val title = titleUseCase.getTitleForMenu(it)
            val id = it.id ?: throw IllegalArgumentException("ID cannot be null here")
            val selected = id?.equals(lastVisited) == true
            BetterMenuItem(title = title, id = id, selected = selected)
        }
}