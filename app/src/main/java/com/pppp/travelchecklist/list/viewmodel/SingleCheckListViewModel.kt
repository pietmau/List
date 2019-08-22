package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer

interface SingleCheckListViewModel : Consumer<SingleCheckListViewModel.ViewEvent> {

    fun states(listId: String): LiveData<ViewState>

    sealed class ViewState {
        data class Data(val travelCheckList: TravelCheckList) : ViewState()
    }

    sealed class ViewEvent {
        data class DeleteItem(val categortyId: Long, val itemId: Long) : ViewEvent()
        data class MoveItems(val cardId: Long, val fromPosition: Int, val toPosition: Int) : ViewEvent()
    }
}


