package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.ViewIntent
import com.pppp.travelchecklist.ViewActionsConsumer

interface SingleCheckListViewModel : ViewActionsConsumer<SingleCheckListViewModel.SingleListViewEvent> {

    fun viewStates(listId: String): LiveData<ViewState>

    data class ViewState(val travelCheckList: TravelCheckList? = null, val showChecked: Boolean = true, val title: String = "", val subTitle: String = "")

    sealed class SingleListViewEvent : ViewIntent {
        data class DeleteItem(val listId: String, val cardId: String, val itemId: String) : SingleListViewEvent()
        data class MoveItem(val listId: String, val cardId: String, val fromPosition: Int, val toPosition: Int) : SingleListViewEvent()
        class ItemChecked(val listId: String, val cardId: String, val itemId: String, val isChecked: Boolean) : SingleListViewEvent()
    }

    sealed class TransientEvent {
        data class ListNotFound(val throwable: Throwable? = null) : TransientEvent()
    }

    val events: LiveData<TransientEvent>
}


