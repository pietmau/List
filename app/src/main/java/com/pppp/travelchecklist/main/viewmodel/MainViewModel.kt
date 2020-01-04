package com.pppp.travelchecklist.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.analytics.MainAnalyticsLogger

class MainViewModel(
    private val mainUseCase: MainUseCase,
    private val settingsUseCase: SettingsUseCase,
    private val analytics: MainAnalyticsLogger,
    override val transientEvents: LiveData<MainTransientEvent>,
    private val internalStates: MutableLiveData<MainViewState>
) : ViewStatesProducer<MainViewState>,
    ViewActionsConsumer<MainViewIntent>,
    TransientEventsProducer<MainTransientEvent>, ViewModel() {

    override val states: LiveData<MainViewState>
        get() {
            settingsUseCase.subscribeToChanges {
                updateCurrentViewState(it)
            }
            return internalStates
        }

    override fun accept(mainViewAction: MainViewIntent) = when (mainViewAction) {
        MainViewIntent.NavMenuOpenSelected -> openNavMenu()
        is MainViewIntent.NavItemSelected -> goToList(mainViewAction.id)
        is MainViewIntent.NewListGenerated -> goToList(mainViewAction.listId)
        MainViewIntent.GetLatestListVisited -> getLatestListVisited()
        MainViewIntent.GoMakeNewList -> goToCreateNewList()
        is MainViewIntent.OnSettingChanged -> settingsUseCase.onUserChangedSettings(mainViewAction.itemId)
        MainViewIntent.DeleteCurrentList -> deleteCurrentList()
        MainViewIntent.OnNoListFound -> emitNewViewState(MainViewState.Empty())
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
        mainUseCase.getLastVisitedList({
            onError(it)
        }, { userLists, lastListId ->
            emitTransientEvent(MainTransientEvent.OpenNavMenu(userLists, lastListId))
        })
    }

    private fun getLatestListVisited() {
        analytics.getLatestListVisited()
        emitNewViewState(MainViewState.Loading())
        mainUseCase.getLastVisitedList({
            emitNewViewState(MainViewState.Empty())
            onError(it)
        }, { _, listId ->
            listId?.let { goToList(listId) } ?: emitNewViewState(MainViewState.Empty())
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

    companion object {
        val KEY = MainViewModel::class.simpleName!!
    }
}

