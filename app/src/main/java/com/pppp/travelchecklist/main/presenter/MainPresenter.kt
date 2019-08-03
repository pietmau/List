package com.pppp.travelchecklist.main.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.login.Consumer
import com.pppp.travelchecklist.login.Producer
import com.pppp.travelchecklist.main.model.MainModel

class MainPresenter(mainModel: MainModel) : Producer<MainPresenter.ViewState>, Consumer<MainPresenter.ViewEvent> {

    override val states: LiveData<ViewState> = MutableLiveData()
    private var checkLists: List<TravelCheckList> = emptyList()

    init {
        mainModel.getUsersLists({
            checkLists = it
        }, {})
    }

    override fun push(viewEvent: ViewEvent) = when (viewEvent) {
        is ViewEvent.NavMenuOpenSelected -> emit(ViewState.OpenNavMenu((checkLists as? List<TravelCheckListImpl>) ?: emptyList()))
        is ViewEvent.NavItemSelected -> onNavItemSelected(viewEvent)
    }

    private fun onNavItemSelected(viewEvent: ViewEvent.NavItemSelected): Unit =
        when (viewEvent.id) {
            R.id.new_list -> emit(ViewState.GoToCreateNewList)
            else -> TODO()
        }

    private fun emit(viewState: ViewState) {
        (states as MutableLiveData).postValue(viewState)
    }

    sealed class ViewState {
        data class OpenNavMenu(val userChecklists: List<TravelCheckListImpl>) : ViewState()
        object GoToCreateNewList : ViewState()
    }

    sealed class ViewEvent {
        object NavMenuOpenSelected : ViewEvent()
        data class NavItemSelected(val id: Int, val title: String?) : ViewEvent()
    }
}