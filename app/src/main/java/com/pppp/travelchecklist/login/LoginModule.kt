package com.pppp.travelchecklist.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
class LoginModule(private val activity: FragmentActivity) {

    @Provides
    fun provideProducer(): Producer<ViewState> = getLoginViewModel()

    @Provides
    fun provideConsumer(): Consumer<ViewEvent> = getLoginViewModel()

    private fun getLoginViewModel() = ViewModelProviders.of(activity, LoginViewModelFactory()).get(LoginViewModel::class.java)
}

class LoginViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = LoginViewModel(FirebaseAuth.getInstance()) as T
}