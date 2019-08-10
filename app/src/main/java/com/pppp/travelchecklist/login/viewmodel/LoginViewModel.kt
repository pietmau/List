package com.pppp.travelchecklist.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer

class LoginViewModel(val model: LoginModel) : Consumer<LoginViewModel.ViewEvent>, Producer<LoginViewModel.ViewState>, ViewModel() {

    override val states: LiveData<ViewState> = MutableLiveData()

    init {
        model.shouldAppBeEnabled({
            checkIfUserIsLoggedIn()
        }, {
            emit(ViewState.Kill)
        })
    }

    private fun checkIfUserIsLoggedIn() {
        if (model.isUserLoggedIn()) {
            emit(ViewState.UserLoggedIn)
        } else {
            emit(ViewState.UserNotLoggedIn)
        }
    }

    private fun emit(userNotLoggedIn: ViewState) {
        (states as MutableLiveData<ViewState>).postValue(userNotLoggedIn)
    }

    override fun push(t: ViewEvent) {

    }

    sealed class ViewState {
        object UserNotLoggedIn : ViewState()
        object UserLoggedIn : ViewState()
        object Kill : ViewState()
    }

    sealed class ViewEvent
}



