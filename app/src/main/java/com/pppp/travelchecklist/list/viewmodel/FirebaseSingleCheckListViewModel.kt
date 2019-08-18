package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.list.model.SingleCheckListModel

class FirebaseSingleCheckListViewModel(
    val listId: String,
    private val model: SingleCheckListModel
) : SingleCheckListViewModel, ViewModel() {

    override val states: MutableLiveData<SingleCheckListViewModel.ViewState> = MutableLiveData()
        get() = field.also {
            model.getUserCheckListAndUpdates(listId, success = {
                states.postValue(SingleCheckListViewModel.ViewState.Data(it))
            })

        }

    override fun accept(event: SingleCheckListViewModel.ViewEvent) =
        when (event) {
            is SingleCheckListViewModel.ViewEvent.DeleteItem -> model.deleteItem(listId, event.categortyId, event.itemId)
            is SingleCheckListViewModel.ViewEvent.MoveItems -> model.moveItems(event.cardId, event.fromPosition, event.toPosition)
        }
}