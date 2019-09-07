package com.pppp.travelchecklist.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.TransientLiveData
import com.pppp.travelchecklist.ViewAction
import com.pppp.travelchecklist.main.model.MainModel
import com.pppp.travelchecklist.TransientEvent
import com.pppp.travelchecklist.ViewState

class MainViewModel(private val model: MainModel) : ViewStatesProducer<MainViewState>, ViewActionsConsumer<MainViewModel.MainViewAction>,
    TransientEventsProducer<MainViewModel.MainTransientEvent>, ViewModel() {
    override val transientEvents: LiveData<MainTransientEvent> = TransientLiveData()
    override val states: LiveData<MainViewState> = MutableLiveData()

    override fun accept(mainViewAction: MainViewAction) = when (mainViewAction) {
        is MainViewAction.NavMenuOpenSelected -> openNavMenu()
        is MainViewAction.NavItemSelected -> onNavItemSelected(mainViewAction)
        is MainViewAction.NewListGenerated -> goToList(mainViewAction.listId)
        is MainViewAction.GetLatestListVisited -> getLatestListVisited()
        is MainViewAction.GoMakeNewList -> goToCreateNewList()
    }

    private fun openNavMenu() {
        model.getLastVisitedList(success = { lastList ->
            val userChecklists = model.checkLists as List<TravelCheckListImpl>
            val transientEvent = MainTransientEvent.OpenNavMenu(userChecklists, lastList)
            emitTransientEvent(transientEvent)
        }, failure = {
            onError(it)
        })
    }

    private fun onFabClicked() {
        if (model.isEmpty) {
            goToCreateNewList()
        } else {
        }
    }

    private fun getLatestListVisited() {
        emitViewState(MainViewState.Loading)
        model.getLastVisitedList({ listId ->
            if (listId != null) {
                goToList(listId)
            } else {
                emitViewState(MainViewState.Empty)
            }
        }, {
            emitViewState(MainViewState.Empty)
            onError(it)
        })
    }

    private fun onError(it: Throwable?) {
        emitTransientEvent(MainTransientEvent.Error(it?.message ?: ""))
    }

    private fun emitViewState(viewState: MainViewState) {
        (states as MutableLiveData<MainViewState>).postValue(viewState)
    }

    private fun onNavItemSelected(mainViewAction: MainViewAction.NavItemSelected) = goToList(mainViewAction.id)

    private fun goToList(listId: String) {
        model.saveLastVisitedList(listId)
        emitViewState(MainViewState.Content)
        emitTransientEvent(MainTransientEvent.GoToList(listId))
    }

    private fun goToCreateNewList() {
        emitTransientEvent(MainTransientEvent.GoToCreateNewList)
    }

    private fun emitTransientEvent(transientEvent: MainTransientEvent) {
        (transientEvents as MutableLiveData<MainTransientEvent>).postValue(transientEvent)
    }

    sealed class MainTransientEvent : TransientEvent {
        data class OpenNavMenu(val userChecklists: List<TravelCheckListImpl>, val lastList: String?) : MainTransientEvent()
        object GoToCreateNewList : MainTransientEvent()
        data class GoToList(val listId: String) : MainTransientEvent()
        data class Error(val message: String) : MainTransientEvent()
    }

    sealed class MainViewAction : ViewAction {
        object NavMenuOpenSelected : MainViewAction()
        object GoMakeNewList : MainViewAction()
        data class NavItemSelected(val id: String) : MainViewAction()
        data class NewListGenerated(val listId: String) : MainViewAction()
        object GetLatestListVisited : MainViewAction()
    }
}

sealed class MainViewState : ViewState {
    object Empty : MainViewState()
    object Content : MainViewState()
    object Loading : MainViewState()
}