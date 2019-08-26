package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer

interface SingleCheckListViewModel : Consumer<SingleCheckListViewModel.ViewEvent> {

    fun states(listId: String): LiveData<ViewState>

    fun getTitle(travelCheckList: TravelCheckList): String

    fun getSubTitle(travelCheckList: TravelCheckList): String

    sealed class ViewState {
        data class Data(val travelCheckList: TravelCheckList) : ViewState()
    }

    sealed class ViewEvent {
        data class DeleteItem(val categortyId: Long, val itemId: Long) : ViewEvent()
        data class MoveItem(val cardId: Long, val fromPosition: Int, val toPosition: Int) : ViewEvent()
        class ItemChecked(val cardId: Long, val itemId: Long, val isChecked: Boolean) : ViewEvent()
    }

    var listId: String
}


