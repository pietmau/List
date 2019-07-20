package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.list.model.SingleCheckListModel

class FirebaseSingleCheckListViewModel(
    val listId: String,
    private val model: SingleCheckListModel
) : SingleCheckListViewModel, ViewModel() {

    override val states: MutableLiveData<SingleCheckListViewModel.ViewState> = MutableLiveData<SingleCheckListViewModel.ViewState>()

    init {
        model.getUserCheckListAndUpdates(listId, success = {
            states.postValue(SingleCheckListViewModel.ViewState.Data(it))
        })
    }

    override fun push(viewEventSingle: SingleCheckListViewModel.ViewEvent) {
        when (viewEventSingle) {
            is SingleCheckListViewModel.ViewEvent.DeleteItem -> delete(viewEventSingle)
        }
    }

    private fun delete(viewEventSingle: SingleCheckListViewModel.ViewEvent.DeleteItem) {
        model.deleteItem(listId, viewEventSingle.categorryPosition, viewEventSingle.itemPosition)
    }

}