package com.pppp.travelchecklist.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.analytics.MainAnalyticsLogger
import com.pppp.travelchecklist.main.MainTransientEvent
import com.pppp.travelchecklist.main.MainViewIntent
import com.pppp.travelchecklist.main.MainViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainUseCase: MainUseCase,
    private val analytics: MainAnalyticsLogger,
    override val transientEvents: LiveData<MainTransientEvent>,
    private val internalStates: MutableLiveData<MainViewState>,
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewStatesProducer<MainViewState>,
    ViewActionsConsumer<MainViewIntent>,
    TransientEventsProducer<MainTransientEvent>, ViewModel() {

    init {
        viewModelScope.launch {
            mainUseCase.settings().collect { updateSettings(it) }
        }
    }

    override val states: LiveData<MainViewState> by lazy { internalStates }

    override fun accept(mainViewAction: MainViewIntent) = when (mainViewAction) {
        MainViewIntent.NavMenuOpenSelected -> openNavMenu()
        is MainViewIntent.NavItemSelected -> goToList(mainViewAction.id)
        is MainViewIntent.NewListGenerated -> goToList(mainViewAction.listId)
        is MainViewIntent.GetLatest -> getLatestListVisited(mainViewAction.path)
        MainViewIntent.GoMakeNewList -> goToCreateNewList()
        is MainViewIntent.OnSettingChanged -> mainUseCase.onUserChangedSettings(mainViewAction.itemId)
        MainViewIntent.DeleteCurrentList -> deleteCurrentList()
        MainViewIntent.OnNoListFound -> onCurrentListNotAvailable()
        is MainViewIntent.SettingsSelected -> emitTransientEvent(MainTransientEvent.GoToSettings)

    }

    private fun onCurrentListNotAvailable() {
        if (mainUseCase.isEmpty()) {
            emitNewViewState(MainViewState.NoListsPresent())
        } else {
            emitNewViewState(MainViewState.LatestListNotAvailable())
            openNavMenu()
        }
    }

    private fun deleteCurrentList() {
        viewModelScope.launch(backgroundDispatcher) {
            mainUseCase.deleteCurrentList()
            onCurrentListNotAvailable()
        }
    }

    private fun updateSettings(settings: Settings) {
        val viewState = (internalStates.value ?: MainViewState.None()).withSettings(settings)
        internalStates.postValue(viewState)
    }

    private fun openNavMenu() {
        analytics.onMainMenuOpen()
        mainUseCase.getLastVisitedList({
            onError(it)
        }, { userLists, lastListId ->
            emitTransientEvent(MainTransientEvent.OpenNavMenu(userLists, lastListId))
        })
    }

    private fun getLatestListVisited(path: List<String>) {
        analytics.getLatestListVisited()
        emitNewViewState(MainViewState.Loading())
        viewModelScope.launch(backgroundDispatcher) {
            mainUseCase.getLastVisitedListId(path)?.let { goToList(it) } ?: emitNewViewState(MainViewState.NoListsPresent())
        }
    }

    private fun onError(it: Throwable?) = emitTransientEvent(MainTransientEvent.Error(it?.message ?: ""))

    private fun emitNewViewState(newViewState: MainViewState) {
        val oldSettings = internalStates.value?.settings ?: Settings()
        val viewState = newViewState.withSettings(oldSettings)
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

