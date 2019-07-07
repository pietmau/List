package com.pppp.travelchecklist.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginViewModel(private val auth: FirebaseAuth) : Consumer<ViewEvent>, Producer<ViewState>, ViewModel() {

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
}

sealed class ViewState {
    object UserNotLoggedIn : ViewState()
    object UserLoggedIn : ViewState()

}

sealed class ViewEvent

interface Consumer<T> {
    fun push(t: T)
}

interface Producer<T> {
    val states: LiveData<T>
}