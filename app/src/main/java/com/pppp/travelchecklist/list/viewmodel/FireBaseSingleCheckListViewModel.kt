package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.travelchecklist.TransientLiveData
import com.pppp.travelchecklist.list.model.SingleCheckListUseCase
import com.pppp.travelchecklist.list.viewmodel.SingleCheckListViewModel.TransientEvent
import com.pppp.travelchecklist.list.viewmodel.SingleCheckListViewModel.ViewState

class FireBaseSingleCheckListViewModel(
    private val singleCheckListUseCase: SingleCheckListUseCase,
    private val titleUsecase: TitleUseCase = TitleUseCaseImpl,
    private val settingsUseCase: ListSettingsUseCase
) : SingleCheckListViewModel, ViewModel() {

    override val events: LiveData<TransientEvent> by lazy { TransientLiveData<TransientEvent>() }

    override fun viewStates(listId: Long): LiveData<ViewState> {
        val liveData = singleCheckListUseCase
            .getUserCheckListAndUpdates(listId)
            .map { list -> list?.let { onListFound(it) } ?: onListNotFound() }
        settingsUseCase.registerVisualizationPreferencesListener {
            val state = (liveData.value ?: ViewState()).copy(showChecked = it)
            (liveData as MutableLiveData).postValue(state)
        }
        return liveData
    }

    private fun onListFound(list: RoomTravelCheckList): ViewState {
        val settings = settingsUseCase.getShowCheckedPreferences()
        val title = titleUsecase.getTitle(list)
        return ViewState(list, settings, title, titleUsecase.getSubTitle(list))
    }

    private fun onListNotFound(): ViewState {
        (events as MutableLiveData).postValue(TransientEvent.ListNotFound())
        return ViewState()
    }

    override fun accept(event: SingleCheckListViewModel.SingleListViewEvent) =
        when (event) {
            is SingleCheckListViewModel.SingleListViewEvent.DeleteItem -> singleCheckListUseCase.deleteItem(
                event.listId,
                event.cardId,
                event.itemId
            )
            is SingleCheckListViewModel.SingleListViewEvent.MoveItem -> singleCheckListUseCase.moveItem(
                event.listId,
                event.cardId,
                event.fromPosition,
                event.toPosition
            )
            is SingleCheckListViewModel.SingleListViewEvent.ItemChecked -> singleCheckListUseCase.checkItem(
                event.listId,
                event.cardId,
                event.itemId,
                event.isChecked
            )
        }
}

fun <IN, OUT> LiveData<IN>.map(function: (IN) -> OUT) = Transformations.map(this, function)