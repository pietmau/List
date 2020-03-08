package com.pppp.travelchecklist.login.di

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.play.core.missingsplits.MissingSplitsManagerFactory
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.login.viewmodel.FirebaseKillSwitch
import com.pppp.travelchecklist.login.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoginModule(private val activity: FragmentActivity) {

    @Provides
    fun provideProducer(loginViewModel: LoginViewModel): ViewStatesProducer<LoginViewModel.LoginViewState> = loginViewModel

    @Provides
    fun provideConsumer(loginViewModel: LoginViewModel): ViewActionsConsumer<LoginViewModel.LoginViewIntent> = loginViewModel

    @Singleton
    @Provides
    fun getLoginViewModel(analyticsLogger: AnalyticsLogger) = ViewModelProviders.of(
        activity, LoginViewModelFactory(analyticsLogger, activity)
    ).get(LoginViewModel::class.java)
}

class LoginViewModelFactory(
    private val analyticsLogger: AnalyticsLogger,
    private val activity: Activity
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val applicationContext = activity.applicationContext
        val packageName = applicationContext.getPackageName()
        val installationSource = applicationContext.getPackageManager().getInstallerPackageName(packageName)
        return LoginViewModel(
            FirebaseKillSwitch(source = installationSource, missingSplitsManager = MissingSplitsManagerFactory.create(applicationContext)), analyticsLogger
        ) as T
    }
}