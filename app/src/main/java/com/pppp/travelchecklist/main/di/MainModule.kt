package com.pppp.travelchecklist.main.di

import com.pppp.travelchecklist.login.Consumer
import com.pppp.travelchecklist.login.Producer
import com.pppp.travelchecklist.main.model.MainModelImpl
import com.pppp.travelchecklist.main.presenter.MainPresenter
import com.pppp.travelchecklist.main.presenter.TransientEvents
import com.pppp.travelchecklist.main.view.MenuCreator
import com.pppp.travelchecklist.main.view.MenuCreatorImpl
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule(private val activity: androidx.fragment.app.FragmentActivity) {

    @Singleton
    @Provides
    fun providePresenter(repo: TravelChecklistRepository) = MainPresenter(MainModelImpl(repo))

    @Provides
    fun provideMenuCreator(creator: MenuCreatorImpl): MenuCreator = creator

    @Provides
    fun provideProducer(presenter: MainPresenter): Producer<MainPresenter.ViewState> = presenter

    @Provides
    fun provideConsumer(presenter: MainPresenter): Consumer<MainPresenter.ViewEvent> = presenter

    @Provides
    fun provideTransientEvdents(presenter: MainPresenter): TransientEvents<MainPresenter.TransientEvent> = presenter
}
