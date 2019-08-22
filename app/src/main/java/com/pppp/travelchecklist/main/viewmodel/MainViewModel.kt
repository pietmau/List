package com.pppp.travelchecklist.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer
import com.pppp.travelchecklist.TransientEvents
import com.pppp.travelchecklist.TransientLiveData
import com.pppp.travelchecklist.main.model.MainModel

class MainViewModel(private val model: MainModel) : Producer<MainViewModel.ViewState>, Consumer<MainViewModel.ViewEvent>,
    TransientEvents<MainViewModel.TransientEvent>, ViewModel() {
    override val transientEvents: LiveData<TransientEvent> = TransientLiveData()
    override val states: LiveData<ViewState> = MutableLiveData()

    override fun accept(viewEvent: ViewEvent) = when (viewEvent) {
        is ViewEvent.NavMenuOpenSelected -> emitTransientEvent(TransientEvent.OpenNavMenu(model.checkLists as List<TravelCheckListImpl>))
        is ViewEvent.NavItemSelected -> onNavItemSelected(viewEvent)
        is ViewEvent.NewListGenerated -> goToList(viewEvent.listId)
        is ViewEvent.GetLatestListVisited -> getLatestListVisited()
        is ViewEvent.NewList -> goToCreateNewList()
    }

    private fun getLatestListVisited() {
        model.getLastVisitedList { listId ->
            if (listId != null) {
                goToList(listId)
            } else {
                emitViewState(ViewState.Empty)
            }
        }
    }

    private fun emitViewState(viewState: ViewState) {
        (states as MutableLiveData<ViewState>).postValue(viewState)
    }

    private fun onNavItemSelected(viewEvent: ViewEvent.NavItemSelected) = goToList(viewEvent.id)

    private fun goToList(listId: String) {
        model.saveLastVisitedList(listId)
        emitViewState(ViewState.Content)
        emitTransientEvent(TransientEvent.GoToList(listId))
    }

    private fun goToCreateNewList() {
        emitTransientEvent(TransientEvent.GoToCreateNewList)
    }

    private fun emitTransientEvent(transientEvent: TransientEvent) {
        (transientEvents as MutableLiveData<TransientEvent>).postValue(transientEvent)
    }

    sealed class TransientEvent {
        data class OpenNavMenu(val userChecklists: List<TravelCheckListImpl>) : TransientEvent()
        object GoToCreateNewList : TransientEvent()
        data class GoToList(val listId: String) : TransientEvent()
    }

    sealed class ViewState {
        object Empty : ViewState()
        object Content : ViewState()
    }

    sealed class ViewEvent {
        object NavMenuOpenSelected : ViewEvent()
        object NewList : ViewEvent()
        data class NavItemSelected(val id: String) : ViewEvent()
        data class NewListGenerated(val listId: String) : ViewEvent()
        object GetLatestListVisited : ViewEvent()
    }
}