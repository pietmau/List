package com.pppp.travelchecklist.list.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.TransientLiveData
import com.pppp.travelchecklist.list.model.SingleCheckListModel

class FireBaseSingleCheckListViewModel(
    private val model: SingleCheckListModel,
    private val titleUsecase: TitleUseCase = TitleUseCaseImpl,
    private val settingsUseCase: ListSettingsUseCase
) : SingleCheckListViewModel, ViewModel() {

    private val viewStates: MutableLiveData<SingleCheckListViewModel.ViewState> by lazy { MutableLiveData<SingleCheckListViewModel.ViewState>() }

    override val events: LiveData<SingleCheckListViewModel.TransientEvent> by lazy { TransientLiveData<SingleCheckListViewModel.TransientEvent>() }

    private fun initialize(listId: String, liveData: MutableLiveData<SingleCheckListViewModel.ViewState>) {
        Log.d("foo", "initialize " + listId + " " + this.toString())
        model.getUserCheckListAndUpdates(listId,
            success = { list ->
                Log.d("foo", "getUserCheckListAndUpdates " + listId)
                val settings = settingsUseCase.getShowCheckedPreferences()
                val title = titleUsecase.getTitle(list)
                liveData.postValue(SingleCheckListViewModel.ViewState(list, settings, title, titleUsecase.getSubTitle(list)))
            },
            failure = {
                (events as MutableLiveData).postValue(SingleCheckListViewModel.TransientEvent.ListNotFound(it))
            })

        settingsUseCase.registerVisualizationPreferencesListener {
            val state = (liveData.value ?: SingleCheckListViewModel.ViewState()).copy(showChecked = it)
            liveData.postValue(state)
        }
    }

    override fun viewStates(listId: String) = viewStates.also { initialize(listId, it) }

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
}
