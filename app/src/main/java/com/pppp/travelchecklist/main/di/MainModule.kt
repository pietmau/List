package com.pppp.travelchecklist.main.di

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.pppp.travelchecklist.main.model.Model
import com.pppp.travelchecklist.main.model.ModelImpl
import com.pppp.travelchecklist.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val frag: Fragment) {

    @Provides
    fun providePresenter(model: Model) = ViewModelProviders.of(frag).get(MainPresenter::class.java)

    fun provideModel(): Model = ModelImpl()
}