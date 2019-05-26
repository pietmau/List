package com.pppp.travelchecklist.main.di

import android.support.v4.app.FragmentActivity
import com.pppp.travelchecklist.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides


@Module
class MainModule(private val activity: FragmentActivity) {

    @Provides
    fun providePresenter() = MainPresenter()

}
