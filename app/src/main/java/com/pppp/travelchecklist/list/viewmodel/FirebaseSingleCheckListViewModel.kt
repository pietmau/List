package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.lazyMap
import com.pppp.travelchecklist.list.model.SingleCheckListModel

class FirebaseSingleCheckListViewModel(
    private val model: SingleCheckListModel
) : SingleCheckListViewModel, ViewModel() {

    private val viewStates: Map<String, LiveData<SingleCheckListViewModel.ViewState>> = lazyMap { listId ->
        val liveData = MutableLiveData<SingleCheckListViewModel.ViewState>()
        model.getUserCheckListAndUpdates(listId, success = { liveData.postValue(SingleCheckListViewModel.ViewState.Data(it)) })
        return@lazyMap liveData
    }

    override fun states(listId: String): LiveData<SingleCheckListViewModel.ViewState> = viewStates.getValue(listId)

    override fun accept(event: SingleCheckListViewModel.ViewEvent) =
        when (event) {
            is SingleCheckListViewModel.ViewEvent.DeleteItem -> TODO()//model.deleteItem(listId, event.categortyId, event.itemId)
            is SingleCheckListViewModel.ViewEvent.MoveItems -> model.moveItems(event.cardId, event.fromPosition, event.toPosition)
        }

}