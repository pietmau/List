package com.pppp.travelchecklist.main.di

import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer
import com.pppp.travelchecklist.main.model.MainModelImpl
import com.pppp.travelchecklist.main.presenter.MainViewModel
import com.pppp.travelchecklist.TransientEvents
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
    fun providePresenter(repo: TravelChecklistRepository) = MainViewModel(MainModelImpl(repo))

    @Provides
    fun provideMenuCreator(creator: MenuCreatorImpl): MenuCreator = creator

    @Provides
    fun provideProducer(viewModel: MainViewModel): Producer<MainViewModel.ViewState> = viewModel

    @Provides
    fun provideConsumer(viewModel: MainViewModel): Consumer<MainViewModel.ViewEvent> = viewModel

    @Provides
    fun provideTransientEvdents(viewModel: MainViewModel): TransientEvents<MainViewModel.TransientEvent> = viewModel
}
