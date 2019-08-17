package com.pppp.travelchecklist.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer
import com.pppp.travelchecklist.utils.NetworkChecker

class LoginViewModel(
    private val killSwitch: KillSwitch,
    private val networkChecker: NetworkChecker
) : Consumer<Any>, Producer<LoginViewModel.ViewState>, ViewModel() {

    override val states: LiveData<ViewState> = MutableLiveData<ViewState>()

    init {
        checkNetwork()
    }

    private fun checkNetwork() {
        networkChecker.checkNetworkConnectionRepeatedly({
            checkIfAppEnabled()
        }, {
            emit(ViewState.Offline(it))
        })
    }

    private fun checkIfAppEnabled() {
        killSwitch.shouldAppBeEnabled({
            checkIfUserIsLoggedIn()
        }, {
            emit(ViewState.Kill)
        })
    }

    private fun checkIfUserIsLoggedIn() {
        if (killSwitch.isUserLoggedIn()) {
            emit(ViewState.UserLoggedIn)
        } else {
            emit(ViewState.UserNotLoggedIn)
        }
    }

    override fun onCleared() {
        networkChecker.cancelNetworkChecks()
    }

    private fun emit(userNotLoggedIn: ViewState) {
        (states as MutableLiveData<ViewState>).postValue(userNotLoggedIn)
    }

    override fun accept(t: Any) = TODO()

    sealed class ViewState {
        data class Offline(val errorMessage: NetworkChecker.ErrorMessage) : ViewState()
        object UserNotLoggedIn : ViewState()
        object UserLoggedIn : ViewState()
        object Kill : ViewState()
    }

}



