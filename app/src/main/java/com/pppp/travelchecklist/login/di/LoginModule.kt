package com.pppp.travelchecklist.login.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.login.viewmodel.FirebaseKillSwitch
import com.pppp.travelchecklist.login.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class LoginModule(private val activity: androidx.fragment.app.FragmentActivity) {

    @Provides
    fun provideProducer(loginViewModel: LoginViewModel): ViewStatesProducer<LoginViewModel.LoginViewState> = loginViewModel

    @Provides
    fun provideConsumer(loginViewModel: LoginViewModel): ViewActionsConsumer<LoginViewModel.LoginViewAction> = loginViewModel

    @Singleton
    @Provides
    fun getLoginViewModel(analyticsLogger: AnalyticsLogger) = ViewModelProviders.of(activity, LoginViewModelFactory(analyticsLogger)).get(LoginViewModel::class.java)
}

class LoginViewModelFactory(val analyticsLogger: AnalyticsLogger) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = LoginViewModel(FirebaseKillSwitch(), analyticsLogger) as T
}