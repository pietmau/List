package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.login.Consumer
import com.pppp.travelchecklist.login.Producer

interface CheckListViewModel : Consumer<CheckListViewModel.ViewEvent>, Producer<CheckListViewModel.ViewState> {

    sealed class ViewState

    sealed class ViewEvent
}


