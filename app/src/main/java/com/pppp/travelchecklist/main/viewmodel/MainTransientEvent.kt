package com.pppp.travelchecklist.main.viewmodel

import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.travelchecklist.TransientEvent

sealed class MainTransientEvent : TransientEvent {
    data class OpenNavMenu(val userChecklists: List<RoomTravelCheckList>, val lastList: Long) : MainTransientEvent()
    object GoToCreateNewList : MainTransientEvent()
    data class GoToList(val listId: Long) : MainTransientEvent()
    data class Error(val message: String) : MainTransientEvent()
}