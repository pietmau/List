package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.login.Consumer
import com.pppp.travelchecklist.login.Producer

interface CheckListViewModel : Consumer<CheckListViewModel.ViewEvent>, Producer<CheckListViewModel.ViewState> {

    sealed class ViewState {
        data class Data(val travelCheckList: TravelCheckList) : ViewState()
    }

    sealed class ViewEvent {
        class Delete : ViewEvent()
    }
}


