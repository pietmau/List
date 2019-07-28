package com.pppp.travelchecklist.main.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.main.model.MainModel

class MainPresenter(mainModel: MainModel) {
    val viewStates: LiveData<ViewState> = MutableLiveData()
    var checkLists: List<TravelCheckList> = emptyList()

    init {
        mainModel.getUsersLists({
            emit(ViewState.GotMenu(it))
            checkLists = it
        }, {})
    }

    private fun emit(viewState: ViewState.GotMenu) {
        (viewStates as MutableLiveData).postValue(viewState)
    }

    sealed class ViewState {
        data class GotMenu(val userChecklists: List<TravelCheckList>) : ViewState()
    }
}