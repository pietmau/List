package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.lazyMap
import com.pppp.travelchecklist.list.model.SingleCheckListModel

class FirebaseSingleCheckListViewModel(
    private val model: SingleCheckListModel,
    private val titleUsecase: TitleUseCase = TitleUseCaseImpl
) : SingleCheckListViewModel, ViewModel() {

    private lateinit var listId: String // TODO remove!!!

    private val viewStates: Map<String, LiveData<SingleCheckListViewModel.ViewState>> = lazyMap { listId ->
        this@FirebaseSingleCheckListViewModel.listId = listId
        val liveData = MutableLiveData<SingleCheckListViewModel.ViewState>()
        model.getUserCheckListAndUpdates(listId, success = { liveData.postValue(SingleCheckListViewModel.ViewState.Data(it)) })
        return@lazyMap liveData
    }

    override fun states(listId: String): LiveData<SingleCheckListViewModel.ViewState> = viewStates.getValue(listId)

    override fun accept(event: SingleCheckListViewModel.ViewEvent) =
        when (event) {
            is SingleCheckListViewModel.ViewEvent.DeleteItem -> model.deleteItem(listId, event.categortyId, event.itemId)
            is SingleCheckListViewModel.ViewEvent.MoveItem -> model.moveItem(event.cardId, event.fromPosition, event.toPosition)
            is SingleCheckListViewModel.ViewEvent.ItemChecked -> model.checkItem(event.cardId, event.itemId, event.isChecked)
        }

    override fun getTitle(travelCheckList: TravelCheckList) = titleUsecase.getTitle(travelCheckList)

    override fun getSubTitle(travelCheckList: TravelCheckList) = titleUsecase.getSubTitle(travelCheckList)
}