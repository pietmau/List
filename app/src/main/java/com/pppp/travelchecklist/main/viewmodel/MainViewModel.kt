package com.pppp.travelchecklist.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer
import com.pppp.travelchecklist.TransientEvents
import com.pppp.travelchecklist.TransientLiveData
import com.pppp.travelchecklist.main.model.MainModel
import java.lang.IndexOutOfBoundsException

class MainViewModel(private val model: MainModel) : Producer<MainViewModel.ViewState>, Consumer<MainViewModel.ViewEvent>,
    TransientEvents<MainViewModel.TransientEvent> {
    override val transientEvents: LiveData<TransientEvent> = TransientLiveData()
    override val states: LiveData<ViewState> = MutableLiveData()

    override fun accept(viewEvent: ViewEvent) = when (viewEvent) {
        is ViewEvent.NavMenuOpenSelected -> emitTransientEvent(TransientEvent.OpenNavMenu(model as List<TravelCheckListImpl>))
        is ViewEvent.NavItemSelected -> onNavItemSelected(viewEvent)
        is ViewEvent.NewListGenerated -> goToList(viewEvent.listId)
        is ViewEvent.OnButtonClicked -> goToCreateNewList()
        is ViewEvent.GetLatestListVisited -> getLatestListVisited()
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

    private fun onNavItemSelected(viewEvent: ViewEvent.NavItemSelected): Unit =
        when (val id = viewEvent.id) {
            R.id.new_list -> goToCreateNewList()
            else -> {
                if (id in model) {
                    goToList(model[id].id!!) // Let it crash.
                } else throw IndexOutOfBoundsException()
            }
        }

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
        data class NavItemSelected(val id: Int, val title: String?) : ViewEvent()
        data class NewListGenerated(val listId: String) : ViewEvent()
        object OnButtonClicked : ViewEvent()
        object GetLatestListVisited : ViewEvent()
    }
}