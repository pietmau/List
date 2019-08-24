package com.pppp.travelchecklist.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer

class LoginViewModel(
    private val killSwitch: KillSwitch
) : Consumer<LoginViewModel.ViewAction>, Producer<LoginViewModel.ViewState>, ViewModel() {

    override val states: LiveData<ViewState> = MutableLiveData<ViewState>()

    init {
        checkIfUserIsLoggedIn()
    }

    private fun checkIfAppEnabled() {
        killSwitch.shouldAppBeEnabled({
            emit(ViewState.AppEnabled)
        }, {
            emit(ViewState.Kill)
        })
    }

    private fun checkIfUserIsLoggedIn() {
        if (killSwitch.isUserLoggedIn()) {
            checkIfAppEnabled()
        } else {
            emit(ViewState.UserNotLoggedIn)
        }
    }

    private fun emit(userNotLoggedIn: ViewState) {
        (states as MutableLiveData<ViewState>).postValue(userNotLoggedIn)
    }

    override fun accept(action: ViewAction) = when (action) {
        is ViewAction.UserLoggedInSuccessfully -> checkIfAppEnabled()
    }

    sealed class ViewState {
        object UserNotLoggedIn : ViewState()
        object AppEnabled : ViewState()
        object Kill : ViewState()
    }

    sealed class ViewAction {
        object UserLoggedInSuccessfully : ViewAction()
    }
}



