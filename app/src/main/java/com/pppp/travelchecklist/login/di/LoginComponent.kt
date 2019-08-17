package com.pppp.travelchecklist.login.di

import com.pppp.travelchecklist.login.view.SplashActivity
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginComponent {

    fun inject(loginActivity: SplashActivity)
}