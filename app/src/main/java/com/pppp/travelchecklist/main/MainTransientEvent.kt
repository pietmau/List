package com.pppp.travelchecklist.main

import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.TransientEvent

sealed class MainTransientEvent : TransientEvent {
    data class OpenNavMenu(val userChecklists: List<TravelCheckListImpl>, val lastList: String?) : MainTransientEvent()
    object GoToCreateNewList : MainTransientEvent()
    data class GoToList(val listId: String) : MainTransientEvent()
    data class Error(val message: String) : MainTransientEvent()
}