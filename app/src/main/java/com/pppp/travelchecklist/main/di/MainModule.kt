package com.pppp.travelchecklist.main.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.main.model.MainModelImpl
import com.pppp.travelchecklist.main.viewmodel.MainViewModel
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.analytics.MainAnalyticsLogger
import com.pppp.travelchecklist.list.viewmodel.TitleUseCase
import com.pppp.travelchecklist.list.viewmodel.TitleUseCaseImpl
import com.pppp.travelchecklist.main.model.Navigator
import com.pppp.travelchecklist.main.model.NavigatorImpl
import com.pppp.travelchecklist.main.view.MenuCreator
import com.pppp.travelchecklist.main.view.MenuCreatorImpl
import com.pppp.travelchecklist.main.viewmodel.MainViewState
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule(private val activity: FragmentActivity) {

    @Singleton
    @Provides
    fun providePresenter(repo: TravelChecklistRepository, logger: AnalyticsLogger) = ViewModelProviders.of(activity, MainViewModelFactory(repo, logger)).get(
        MainViewModel::class.java
    )

    @Provides
    fun provideMenuCreator(creator: MenuCreatorImpl): MenuCreator = creator

    @Provides
    fun provideProducer(viewModel: MainViewModel): ViewStatesProducer<MainViewState> = viewModel

    @Provides
    fun provideConsumer(viewModel: MainViewModel): ViewActionsConsumer<MainViewModel.MainViewAction> = viewModel

    @Provides
    fun provideTransientEvdents(viewModel: MainViewModel): TransientEventsProducer<MainViewModel.MainTransientEvent> = viewModel

    @Provides
    fun provideNavigationActionMapper(): Navigator = NavigatorImpl

    @Provides
    fun provideTitleUseCase(): TitleUseCase = TitleUseCaseImpl

    class MainViewModelFactory(
        private val repo: TravelChecklistRepository, val logger: MainAnalyticsLogger
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(MainModelImpl(repo), logger) as T
    }
}
