package com.pppp.travelchecklist.login.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer
import com.pppp.travelchecklist.login.viewmodel.FirebaseKillSwitch
import com.pppp.travelchecklist.login.viewmodel.LoginViewModel
import com.pppp.travelchecklist.utils.NetworkChecker
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class LoginModule(private val activity: androidx.fragment.app.FragmentActivity) {

    @Provides
    fun provideProducer(loginViewModel: LoginViewModel): Producer<LoginViewModel.ViewState> = loginViewModel

    @Provides
    fun provideConsumer(loginViewModel: LoginViewModel): Consumer<Any> = loginViewModel

    @Singleton
    @Provides
    fun getLoginViewModel(checker: NetworkChecker) = ViewModelProviders.of(activity, LoginViewModelFactory(checker)).get(LoginViewModel::class.java)
}

class LoginViewModelFactory(val checker: NetworkChecker) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = LoginViewModel(FirebaseKillSwitch(), checker) as T
}