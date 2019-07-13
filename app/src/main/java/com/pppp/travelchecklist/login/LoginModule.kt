package com.pppp.travelchecklist.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
class LoginModule(private val activity: androidx.fragment.app.FragmentActivity) {

    @Provides
    fun provideProducer(): Producer<ViewState> = getLoginViewModel()

    @Provides
    fun provideConsumer(): Consumer<ViewEvent> = getLoginViewModel()

    private fun getLoginViewModel() = ViewModelProviders.of(activity, LoginViewModelFactory()).get(LoginViewModel::class.java)
}

class LoginViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = LoginViewModel(FirebaseAuth.getInstance()) as T
}