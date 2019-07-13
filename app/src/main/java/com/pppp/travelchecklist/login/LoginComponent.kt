package com.pppp.travelchecklist.login

import dagger.Subcomponent

@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginComponent {

    fun inject(loginActivity: SplashActivity)
}