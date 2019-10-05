package com.pppp.travelchecklist.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.ViewAction
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewState
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.analytics.LoginAnalyticsLogger

class LoginViewModel(
    private val killSwitch: KillSwitch,
    private val analytics: LoginAnalyticsLogger
) : ViewActionsConsumer<LoginViewModel.LoginViewAction>, ViewStatesProducer<LoginViewModel.LoginViewState>, ViewModel() {

    override val states: LiveData<LoginViewState> = MutableLiveData()

    init {
        checkIfUserIsLoggedIn()
    }

    private fun checkIfAppEnabled() {
        killSwitch.shouldAppBeEnabled({
            emit(LoginViewState.AppEnabled)
        }, {
            analytics.onAppNotEnabled(it)
            emit(LoginViewState.Kill)
        })
    }

    private fun checkIfUserIsLoggedIn() {
        if (killSwitch.isUserLoggedIn()) {
            analytics.onUserAlreadyLoggedIn()
            checkIfAppEnabled()
        } else {
            analytics.onLoginFlowStart()
            emit(LoginViewState.UserNotLoggedIn)
        }
    }

    private fun emit(userNotLoggedIn: LoginViewState) {
        (states as MutableLiveData<LoginViewState>).postValue(userNotLoggedIn)
    }

    override fun accept(action: LoginViewAction) = when (action) {
        is LoginViewAction.UserLoggedInSuccessfully -> {
            analytics.onLoginFlowSuccess()
            checkIfAppEnabled()
        }
    }

    sealed class LoginViewState : ViewState {
        object UserNotLoggedIn : LoginViewState()
        object AppEnabled : LoginViewState()
        object Kill : LoginViewState()
    }

    sealed class LoginViewAction : ViewAction {
        object UserLoggedInSuccessfully : LoginViewAction()
    }
}



