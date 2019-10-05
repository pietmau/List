package com.pppp.travelchecklist.main.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.main.viewmodel.MainViewModel
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.analytics.MainAnalyticsLogger
import com.pppp.travelchecklist.list.viewmodel.TitleUseCase
import com.pppp.travelchecklist.list.viewmodel.TitleUseCaseImpl
import com.pppp.travelchecklist.main.model.MainModel
import com.pppp.travelchecklist.main.model.MainModelImpl
import com.pppp.travelchecklist.main.model.Navigator
import com.pppp.travelchecklist.main.model.NavigatorImpl
import com.pppp.travelchecklist.main.view.MenuCreator
import com.pppp.travelchecklist.main.view.MenuCreatorImpl
import com.pppp.travelchecklist.main.viewmodel.FirebaseMainUseCase
import com.pppp.travelchecklist.main.viewmodel.MainTransientEvent
import com.pppp.travelchecklist.main.viewmodel.MainViewAction
import com.pppp.travelchecklist.main.viewmodel.MainViewState
import com.pppp.travelchecklist.main.viewmodel.SharedPreferencesSettingsUseCase
import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule(private val activity: FragmentActivity) {

    @Singleton
    @Provides
    fun providePresenter(
        repo: TravelChecklistRepository,
        logger: AnalyticsLogger,
        prefs: PreferencesWrapper
    ) =
        ViewModelProviders.of(activity, MainViewModelFactory(logger, MainModelImpl(repo), prefs)).get(MainViewModel::class.java)

    @Provides
    fun provideMenuCreator(creator: MenuCreatorImpl): MenuCreator = creator

    @Provides
    fun provideProducer(viewModel: MainViewModel): ViewStatesProducer<MainViewState> = viewModel

    @Provides
    fun provideConsumer(viewModel: MainViewModel): ViewActionsConsumer<MainViewAction> = viewModel

    @Provides
    fun provideTransientEvdents(viewModel: MainViewModel): TransientEventsProducer<MainTransientEvent> = viewModel

    @Provides
    fun provideNavigationActionMapper(): Navigator = NavigatorImpl

    @Provides
    fun provideTitleUseCase(): TitleUseCase = TitleUseCaseImpl

    class MainViewModelFactory(
        val logger: MainAnalyticsLogger,
        val model: MainModel,
        val prefs: PreferencesWrapper
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(
            FirebaseMainUseCase(model),
            SharedPreferencesSettingsUseCase(prefs),
            logger
        ) as T
    }
}
