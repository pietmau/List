package com.pppp.travelchecklist.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(private val auth: FirebaseAuth = FirebaseAuth.getInstance()) : Consumer<LoginViewModel.ViewEvent>, Producer<LoginViewModel.ViewState>,
    ViewModel() {

    override val states: MutableLiveData<ViewState> = MutableLiveData()

    init {
        start()
    }

    private fun start() {
        if (auth.getCurrentUser() == null) {
            states.postValue(ViewState.UserNotLoggedIn)
        } else {
            states.postValue(ViewState.UserLoggedIn)
        }
    }

    override fun push(t: ViewEvent) {

    }

    sealed class ViewState {
        object UserNotLoggedIn : ViewState()
        object UserLoggedIn : ViewState()

    }

    sealed class ViewEvent
}



