package com.pppp.travelchecklist.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.TransientLiveData
import com.pppp.travelchecklist.analytics.MainAnalyticsLogger

private val KEY = MainViewModel::class.simpleName!!

class MainViewModel(
    private val mainUseCase: MainUseCase,
    private val settingsUseCase: SettingsUseCase,
    private val analytics: MainAnalyticsLogger,
    private val handle: SavedStateHandle
) : ViewStatesProducer<MainViewState>,
    ViewActionsConsumer<MainViewAction>,
    TransientEventsProducer<MainTransientEvent>, ViewModel() {

    override val transientEvents: LiveData<MainTransientEvent> = TransientLiveData()
    private val internalStates: MutableLiveData<MainViewState> = handle.getLiveData(KEY)

    override val states: LiveData<MainViewState>
        get() {
            settingsUseCase.subscribeToChanges {
                updateCurrentViewState(it)
            }
            return internalStates
        }

    override fun accept(mainViewAction: MainViewAction) = when (mainViewAction) {
        MainViewAction.NavMenuOpenSelected -> openNavMenu()
        is MainViewAction.NavItemSelected -> goToList(mainViewAction.id)
        is MainViewAction.NewListGenerated -> goToList(mainViewAction.listId)
        MainViewAction.GetLatestListVisited -> getLatestListVisited()
        MainViewAction.GoMakeNewList -> goToCreateNewList()
        is MainViewAction.OnSettingChanged -> settingsUseCase.onUserChangedSettings(mainViewAction.itemId)
        MainViewAction.DeleteCurrentList -> deleteCurrentList()
        MainViewAction.OnNoListFound -> emitNewViewState(MainViewState.Empty())
    }

    private fun deleteCurrentList() {
        mainUseCase.deleteCurrentList()
        if (mainUseCase.isEmpty()) {
            emitNewViewState(MainViewState.Empty())
        } else {
            emitNewViewState(MainViewState.Content())
            openNavMenu()
        }
    }

    private fun updateCurrentViewState(settings: MainViewState.Settings) {
        val viewState = internalStates.value ?: MainViewState.None()
        emitNewViewState(viewState.withNewSettings(settings))
    }

    private fun openNavMenu() {
        analytics.onMainMenuOpen()
        mainUseCase.getLastVisitedList({ userLists, lastListId ->
            emitTransientEvent(MainTransientEvent.OpenNavMenu(userLists, lastListId))
        }, {
            onError(it)
        })
    }

    private fun getLatestListVisited() {
        analytics.getLatestListVisited()
        emitNewViewState(MainViewState.Loading())
        mainUseCase.getLastVisitedList({ _, listId ->
            listId?.let { goToList(listId) } ?: emitNewViewState(MainViewState.Empty())
        }, {
            emitNewViewState(MainViewState.Empty())
            onError(it)
        })
    }

    private fun onError(it: Throwable?) = emitTransientEvent(MainTransientEvent.Error(it?.message ?: ""))

    private fun emitNewViewState(newViewState: MainViewState) {
        val oldSettings = internalStates.value?.settings
        val viewState = newViewState.makeCopyReplacingOldSettingsWithNew(oldSettings = oldSettings)
        internalStates.postValue(viewState)
    }

    private fun goToList(listId: String) {
        analytics.goToList(listId)
        mainUseCase.saveLastVisitedList(listId)
        emitNewViewState(MainViewState.Content())
        emitTransientEvent(MainTransientEvent.GoToList(listId))
    }

    private fun goToCreateNewList() {
        analytics.goToCreateNewList()
        emitTransientEvent(MainTransientEvent.GoToCreateNewList)
    }

    private fun emitTransientEvent(transientEvent: MainTransientEvent) = (transientEvents as MutableLiveData<MainTransientEvent>).postValue(transientEvent)

}

