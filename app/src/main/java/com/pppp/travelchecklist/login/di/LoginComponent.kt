package com.pppp.travelchecklist.login.di

import androidx.fragment.app.FragmentActivity
import com.pppp.travelchecklist.login.view.SplashActivity
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: FragmentActivity): LoginComponent
    }

    fun inject(loginActivity: SplashActivity)
}