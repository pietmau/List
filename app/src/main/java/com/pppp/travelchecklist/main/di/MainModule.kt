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
import com.pppp.travelchecklist.TransientLiveData
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.analytics.MainAnalyticsLogger
import com.pppp.travelchecklist.list.viewmodel.TitleUseCase
import com.pppp.travelchecklist.list.viewmodel.TitleUseCaseImpl
import com.pppp.travelchecklist.main.model.MainModel
import com.pppp.travelchecklist.main.model.MainModelImpl
import com.pppp.travelchecklist.main.model.Navigator
import com.pppp.travelchecklist.main.model.NavigatorImpl
import com.pppp.travelchecklist.navigation.MenuCreator
import com.pppp.travelchecklist.navigation.MenuCreatorImpl
import com.pppp.travelchecklist.main.viewmodel.FirebaseMainUseCase
import com.pppp.travelchecklist.main.MainTransientEvent
import com.pppp.travelchecklist.main.MainViewIntent
import com.pppp.travelchecklist.main.MainViewState
import com.pppp.travelchecklist.main.viewmodel.SettingsUseCase
import com.pppp.travelchecklist.main.viewmodel.SharedPreferencesSettingsUseCase
import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object MainModule {

    @JvmStatic
    @Singleton
    @Provides
    fun providePresenter(
        repo: TravelChecklistRepository,
        logger: AnalyticsLogger,
        settingsUseCase: SettingsUseCase,
        activity: FragmentActivity
    ) =
        ViewModelProviders.of(activity, MainViewModelFactory(logger, MainModelImpl(repo), settingsUseCase, activity)).get(MainViewModel::class.java)

    @JvmStatic
    @Provides
    fun provideMenuCreator(creator: MenuCreatorImpl): MenuCreator = creator

    @JvmStatic
    @Provides
    fun provideProducer(viewModel: MainViewModel): ViewStatesProducer<MainViewState> = viewModel

    @JvmStatic
    @Provides
    fun provideConsumer(viewModel: MainViewModel): ViewActionsConsumer<MainViewIntent> = viewModel

    @JvmStatic
    @Provides
    fun provideTransientEvdents(viewModel: MainViewModel): TransientEventsProducer<MainTransientEvent> = viewModel

    @JvmStatic
    @Provides
    fun provideNavigationActionMapper(): Navigator = NavigatorImpl

    @JvmStatic
    @Provides
    fun provideTitleUseCase(): TitleUseCase = TitleUseCaseImpl

    @JvmStatic
    @Provides
    fun provideSettingsUseCase(prefs: PreferencesWrapper): SettingsUseCase = SharedPreferencesSettingsUseCase(prefs)

    class MainViewModelFactory(
        val logger: MainAnalyticsLogger,
        val model: MainModel,
        val settingsUseCase: SettingsUseCase,
        activity: SavedStateRegistryOwner
    ) : AbstractSavedStateViewModelFactory(activity, null) {
        override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T = MainViewModel(
            FirebaseMainUseCase(model, settingsUseCase),
            logger,
            TransientLiveData<MainTransientEvent>(),
            handle.getLiveData(MainViewModel.KEY)
        ) as T

    }
}
