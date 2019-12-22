package com.pppp.travelchecklist.main.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.savedstate.SavedStateRegistryOwner
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.main.viewmodel.MainViewModel
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.analytics.MainAnalyticsLogger
import com.pppp.travelchecklist.application.di.ApplicationScope
import com.pppp.travelchecklist.list.viewmodel.TitleUseCase
import com.pppp.travelchecklist.list.viewmodel.TitleUseCaseImpl
import com.pppp.travelchecklist.main.model.MainUseCase
import com.pppp.travelchecklist.main.model.Navigator
import com.pppp.travelchecklist.main.model.NavigatorImpl
import com.pppp.travelchecklist.main.model.RoomMainUseCase
import com.pppp.travelchecklist.navigation.MenuCreator
import com.pppp.travelchecklist.navigation.MenuCreatorImpl
import com.pppp.travelchecklist.main.viewmodel.MainTransientEvent
import com.pppp.travelchecklist.main.viewmodel.MainViewAction
import com.pppp.travelchecklist.main.viewmodel.MainViewState
import com.pppp.travelchecklist.main.viewmodel.SettingsUseCase
import com.pppp.travelchecklist.main.viewmodel.SharedPreferencesSettingsUseCase
import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import dagger.Module
import dagger.Provides

@ApplicationScope
@Module
class MainModule(private val activity: FragmentActivity) {

    @Provides
    fun providePresenter(
        logger: AnalyticsLogger,
        settingsUseCase: SettingsUseCase,
        repo: TravelChecklistRepository
    ) =
        ViewModelProviders.of(activity, MainViewModelFactory(logger, settingsUseCase, activity, repo)).get(MainViewModel::class.java)

    @Provides
    fun provideMenuCreator(creator: MenuCreatorImpl, repo: TravelChecklistRepository): MainUseCase = RoomMainUseCase(repo)

    @Provides
    fun provideMainUseCase(creator: MenuCreatorImpl): MenuCreator = creator

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

    @Provides
    fun provideSettingsUseCase(prefs: PreferencesWrapper): SettingsUseCase = SharedPreferencesSettingsUseCase(prefs)

    class MainViewModelFactory(
        val logger: MainAnalyticsLogger,
        val settingsUseCase: SettingsUseCase,
        activity: SavedStateRegistryOwner,
        val repo: TravelChecklistRepository
    ) : AbstractSavedStateViewModelFactory(activity, null) {
        override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T = MainViewModel(
            RoomMainUseCase(repo),
            settingsUseCase,
            logger,
            handle
        ) as T

    }
}
