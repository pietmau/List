package com.pppp.travelchecklist.main.di

import com.pppp.travelchecklist.main.model.MainModelImpl
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.view.MenuCreator
import com.pppp.travelchecklist.main.view.MenuCreatorImpl
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: androidx.fragment.app.FragmentActivity) {

    @Provides
    fun providePresenter(repo: TravelChecklistRepository) = MainPresenter(MainModelImpl(repo))

    @Provides
    fun provideMenuCreator(creator: MenuCreatorImpl): MenuCreator = creator

}
