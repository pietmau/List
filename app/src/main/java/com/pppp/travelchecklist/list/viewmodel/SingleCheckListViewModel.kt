package com.pppp.travelchecklist.list.viewmodel

import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer

interface SingleCheckListViewModel : Consumer<SingleCheckListViewModel.ViewEvent>, Producer<SingleCheckListViewModel.ViewState> {

    sealed class ViewState {
        data class Data(val travelCheckList: TravelCheckList) : ViewState()
    }

    sealed class ViewEvent {
        class DeleteItem(val categortyId: Long, val itemId: Long) : ViewEvent()
    }
}


