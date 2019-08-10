package com.pppp.travelchecklist.main.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer
import com.pppp.travelchecklist.TransientEvents
import com.pppp.travelchecklist.main.model.MainModel
import java.lang.IndexOutOfBoundsException

class MainViewModel(mainModel: MainModel) : Producer<MainViewModel.ViewState>, Consumer<MainViewModel.ViewEvent>,
    TransientEvents<MainViewModel.TransientEvent> {

    override val states: LiveData<ViewState> = MutableLiveData()
    private var checkLists: List<TravelCheckList> = emptyList()
    private var observer: ((TransientEvent) -> Unit)? = null

    init {
        mainModel.getUsersLists({
            checkLists = it
        }, {})
    }

    override fun push(viewEvent: ViewEvent) = when (viewEvent) {
        is ViewEvent.NavMenuOpenSelected -> emitTransientEvent(TransientEvent.OpenNavMenu((checkLists as? List<TravelCheckListImpl>) ?: emptyList()))
        is ViewEvent.NavItemSelected -> onNavItemSelected(viewEvent)
        is ViewEvent.NewListGenerated -> emitTransientEvent(TransientEvent.GoToList(viewEvent.listId))
    }

    override fun subscribe(observer: ((TransientEvent) -> Unit)?) {
        this.observer = observer
    }

    private fun onNavItemSelected(viewEvent: ViewEvent.NavItemSelected): Unit =
        when (val id = viewEvent.id) {
            R.id.new_list -> emitTransientEvent(TransientEvent.GoToCreateNewList)
            else -> {
                if (positionIsInRange(id)) {
                    emitTransientEvent(TransientEvent.GoToList(checkLists[id].id!!))
                } else throw IndexOutOfBoundsException()
            }
        }

    private fun emitTransientEvent(transientEvent: TransientEvent) {
        observer?.invoke(transientEvent)
    }

    private fun positionIsInRange(position: Int) = (position in 0..(checkLists.size - 1))

    sealed class TransientEvent {
        data class OpenNavMenu(val userChecklists: List<TravelCheckListImpl>) : TransientEvent()
        object GoToCreateNewList : TransientEvent()
        data class GoToList(val listId: String) : TransientEvent()
    }

    sealed class ViewState

    sealed class ViewEvent {
        object NavMenuOpenSelected : ViewEvent()
        data class NavItemSelected(val id: Int, val title: String?) : ViewEvent()
        data class NewListGenerated(val listId: String) : ViewEvent()
    }
}