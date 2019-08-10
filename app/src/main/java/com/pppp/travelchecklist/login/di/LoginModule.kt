package com.pppp.travelchecklist.login.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer
import com.pppp.travelchecklist.login.viewmodel.FirebaseLoginModel
import com.pppp.travelchecklist.login.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides

@Module
class LoginModule(private val activity: androidx.fragment.app.FragmentActivity) {

    @Provides
    fun provideProducer(): Producer<LoginViewModel.ViewState> = getLoginViewModel()

    @Provides
    fun provideConsumer(): Consumer<LoginViewModel.ViewEvent> = getLoginViewModel()

    private fun getLoginViewModel() = ViewModelProviders.of(activity, LoginViewModelFactory()).get(LoginViewModel::class.java)
}

class LoginViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = LoginViewModel(FirebaseLoginModel()) as T
}