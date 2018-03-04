package com.pppp.travelchecklist.main.di

import com.pppp.travelchecklist.main.model.Model
import com.pppp.travelchecklist.main.model.ModelImpl
import com.pppp.travelchecklist.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun providePresenter(model: Model) = MainPresenter(model)

    @Provides
    fun provideModel(): Model = ModelImpl()
}