package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.utils.lazyMap
import com.pppp.travelchecklist.list.model.SingleCheckListModel
import com.pppp.travelchecklist.main.viewmodel.SettingsUseCase

class FirebaseSingleCheckListViewModel(
    private val model: SingleCheckListModel,
    private val titleUsecase: TitleUseCase = TitleUseCaseImpl,
    private val settingsUseCase: ListSettingsUseCase
) : SingleCheckListViewModel, ViewModel() {

    private val viewStates: Map<String, LiveData<SingleCheckListViewModel.ViewState>> = lazyMap { listId ->
        val liveData = MutableLiveData<SingleCheckListViewModel.ViewState>()
        initialize(listId, liveData)
        return@lazyMap liveData
    }

    private fun initialize(
        listId: String,
        liveData: MutableLiveData<SingleCheckListViewModel.ViewState>
    ) {
        model.getUserCheckListAndUpdates(listId, success = { liveData.postValue(SingleCheckListViewModel.ViewState.Data(it)) })
        settingsUseCase.registerVisualizationPreferencesListener {

        }
    }

    override fun states(listId: String): LiveData<SingleCheckListViewModel.ViewState> = viewStates.getValue(listId)

    override fun accept(eventSingleList: SingleCheckListViewModel.SingleListViewEvent) =
        when (eventSingleList) {
            is SingleCheckListViewModel.SingleListViewEvent.DeleteItem -> model.deleteItem(
                eventSingleList.listId,
                eventSingleList.cardId,
                eventSingleList.itemId
            )
            is SingleCheckListViewModel.SingleListViewEvent.MoveItem -> model.moveItem(
                eventSingleList.listId,
                eventSingleList.cardId,
                eventSingleList.fromPosition,
                eventSingleList.toPosition
            )
            is SingleCheckListViewModel.SingleListViewEvent.ItemChecked -> model.checkItem(
                eventSingleList.listId,
                eventSingleList.cardId,
                eventSingleList.itemId,
                eventSingleList.isChecked
            )
        }

    override fun getTitle(travelCheckList: TravelCheckList) = titleUsecase.getTitle(travelCheckList)

    override fun getSubTitle(travelCheckList: TravelCheckList) = titleUsecase.getSubTitle(travelCheckList)
}