package com.pppp.travelchecklist.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.TransientLiveData
import com.pppp.travelchecklist.list.model.SingleCheckListUseCase

class FireBaseSingleCheckListViewModel(
    private val singleCheckListUseCase: SingleCheckListUseCase,
    private val titleUsecase: TitleUseCase = TitleUseCaseImpl,
    private val settingsUseCase: ListSettingsUseCase
) : SingleCheckListViewModel, ViewModel() {

    private val viewStates: MutableLiveData<SingleCheckListViewModel.ViewState> by lazy { MutableLiveData<SingleCheckListViewModel.ViewState>() }

    override val events: LiveData<SingleCheckListViewModel.TransientEvent> by lazy { TransientLiveData<SingleCheckListViewModel.TransientEvent>() }

    private fun initialize(listId: Long, liveData: MutableLiveData<SingleCheckListViewModel.ViewState>) {
        singleCheckListUseCase.getUserCheckListAndUxxpdates(listId).observeForever{

            singleCheckListUseCase.ffff(it)
        }
//        singleCheckListUseCase.getUserCheckListAndUpdates(listId,
//            success = { list ->
//                val settings = settingsUseCase.getShowCheckedPreferences()
//                val title = titleUsecase.getTitle(list)
//                liveData.postValue(SingleCheckListViewModel.ViewState(list, settings, title, titleUsecase.getSubTitle(list)))
//            },
//            failure = {
//                (events as MutableLiveData).postValue(SingleCheckListViewModel.TransientEvent.ListNotFound(it))
//            })
//
//        settingsUseCase.registerVisualizationPreferencesListener {
//            val state = (liveData.value ?: SingleCheckListViewModel.ViewState()).copy(showChecked = it)
//            liveData.postValue(state)
//        }
    }

    override fun viewStates(listId: Long) = viewStates.also { initialize(listId, it) }

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
