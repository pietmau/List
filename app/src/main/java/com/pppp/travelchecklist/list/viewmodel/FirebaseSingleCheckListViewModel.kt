package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.utils.lazyMap
import com.pppp.travelchecklist.list.model.SingleCheckListModel

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
        model.getUserCheckListAndUpdates(listId) {
            val data = SingleCheckListViewModel.ViewState(it, settingsUseCase.getShowCheckedPreferences(), getTitle(it), getSubTitle(it))
            liveData.postValue(data)
        }
        settingsUseCase.registerVisualizationPreferencesListener {
            val value = liveData.value ?: SingleCheckListViewModel.ViewState()
            val state = value.copy(showChecked = it)
            liveData.postValue(state)
        }
    }

    override fun states(listId: String): LiveData<SingleCheckListViewModel.ViewState> = viewStates.getValue(listId)

    override fun accept(event: SingleCheckListViewModel.SingleListViewEvent) =
        when (event) {
            is SingleCheckListViewModel.SingleListViewEvent.DeleteItem -> model.deleteItem(
                event.listId,
                event.cardId,
                event.itemId
            )
            is SingleCheckListViewModel.SingleListViewEvent.MoveItem -> model.moveItem(
                event.listId,
                event.cardId,
                event.fromPosition,
                event.toPosition
            )
            is SingleCheckListViewModel.SingleListViewEvent.ItemChecked -> model.checkItem(
                event.listId,
                event.cardId,
                event.itemId,
                event.isChecked
            )
        }

    override fun getTitle(travelCheckList: TravelCheckList) = titleUsecase.getTitle(travelCheckList)

    override fun getSubTitle(travelCheckList: TravelCheckList) = titleUsecase.getSubTitle(travelCheckList)
}