package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.ViewAction
import com.pppp.travelchecklist.ViewActionsConsumer

interface SingleCheckListViewModel : ViewActionsConsumer<SingleCheckListViewModel.SingleListViewEvent> {

    fun states(listId: String): LiveData<ViewState>

    fun getTitle(travelCheckList: TravelCheckList): String

    fun getSubTitle(travelCheckList: TravelCheckList): String

    sealed class ViewState {
        data class Data(val travelCheckList: TravelCheckList) : ViewState()
    }

    sealed class SingleListViewEvent : ViewAction {
        data class DeleteItem(val categortyId: Long, val itemId: Long) : SingleListViewEvent()
        data class MoveItem(val cardId: Long, val fromPosition: Int, val toPosition: Int) : SingleListViewEvent()
        class ItemChecked(val cardId: Long, val itemId: Long, val isChecked: Boolean) : SingleListViewEvent()
    }

    var listId: String
}


