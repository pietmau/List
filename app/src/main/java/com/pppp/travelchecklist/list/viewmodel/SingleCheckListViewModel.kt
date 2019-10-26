package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.ViewAction
import com.pppp.travelchecklist.ViewActionsConsumer

interface SingleCheckListViewModel : ViewActionsConsumer<SingleCheckListViewModel.SingleListViewEvent> {

    fun states(listId: String): LiveData<ViewState>

    fun getTitle(travelCheckList: TravelCheckList): String

    fun getSubTitle(travelCheckList: TravelCheckList): String

    data class ViewState(val travelCheckList: TravelCheckList? = null, val showChecked: Boolean = true, val title: String = "", val subTitle: String = "")

    sealed class SingleListViewEvent : ViewAction {
        data class DeleteItem(val listId: String, val cardId: String, val itemId: String) : SingleListViewEvent()
        data class MoveItem(val listId: String, val cardId: String, val fromPosition: Int, val toPosition: Int) : SingleListViewEvent()
        class ItemChecked(val listId: String, val cardId: String, val itemId: String, val isChecked: Boolean) : SingleListViewEvent()
    }
}


