package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.utils.lazyMap
import com.pppp.travelchecklist.list.model.SingleCheckListModel

class FirebaseSingleCheckListViewModel(
    private val model: SingleCheckListModel,
    private val titleUsecase: TitleUseCase = TitleUseCaseImpl
) : SingleCheckListViewModel, ViewModel() {

    override lateinit var listId: String // TODO remove!!!

    private val viewStates: Map<String, LiveData<SingleCheckListViewModel.ViewState>> = lazyMap { listId ->
        this@FirebaseSingleCheckListViewModel.listId = listId
        val liveData = MutableLiveData<SingleCheckListViewModel.ViewState>()
        model.getUserCheckListAndUpdates(listId, success = { liveData.postValue(SingleCheckListViewModel.ViewState.Data(it)) })
        return@lazyMap liveData
    }

    override fun states(listId: String): LiveData<SingleCheckListViewModel.ViewState> = viewStates.getValue(listId)

    override fun accept(eventSingleList: SingleCheckListViewModel.SingleListViewEvent) =
        when (eventSingleList) {
            is SingleCheckListViewModel.SingleListViewEvent.DeleteItem -> model.deleteItem(listId, eventSingleList.categortyId, eventSingleList.itemId)
            is SingleCheckListViewModel.SingleListViewEvent.MoveItem -> model.moveItem(eventSingleList.cardId, eventSingleList.fromPosition, eventSingleList.toPosition)
            is SingleCheckListViewModel.SingleListViewEvent.ItemChecked -> model.checkItem(eventSingleList.cardId, eventSingleList.itemId, eventSingleList.isChecked)
        }

    override fun getTitle(travelCheckList: TravelCheckList) = titleUsecase.getTitle(travelCheckList)

    override fun getSubTitle(travelCheckList: TravelCheckList) = titleUsecase.getSubTitle(travelCheckList)
}