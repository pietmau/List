package com.pppp.travelchecklist.main.di

import androidx.fragment.app.FragmentActivity
import com.pppp.travelchecklist.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides


@Module
class MainModule(private val activity: androidx.fragment.app.FragmentActivity) {

    @Provides
    fun providePresenter() = MainPresenter()

}
