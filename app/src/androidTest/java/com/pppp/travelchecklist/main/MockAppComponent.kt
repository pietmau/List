package com.pppp.travelchecklist.main

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.savedstate.SavedStateRegistryOwner
import com.google.firebase.analytics.FirebaseAnalytics
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.TransientLiveData
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.analytics.AnalyticsLogger
import com.pppp.travelchecklist.analytics.MainAnalyticsLogger
import com.pppp.travelchecklist.application.di.AppComponent
import com.pppp.travelchecklist.application.di.AppModule
import com.pppp.travelchecklist.createlist.NewListActivity
import com.pppp.travelchecklist.list.viewmodel.TitleUseCase
import com.pppp.travelchecklist.list.viewmodel.TitleUseCaseImpl
import com.pppp.travelchecklist.main.di.MainModule
import com.pppp.travelchecklist.main.di.MainSubComponent
import com.pppp.travelchecklist.main.model.MainModel
import com.pppp.travelchecklist.main.model.MainModelImpl
import com.pppp.travelchecklist.main.model.Navigator
import com.pppp.travelchecklist.main.model.NavigatorImpl
import com.pppp.travelchecklist.main.viewmodel.FirebaseMainUseCase
import com.pppp.travelchecklist.main.viewmodel.MainTransientEvent
import com.pppp.travelchecklist.main.viewmodel.MainViewIntent
import com.pppp.travelchecklist.main.viewmodel.MainViewModel
import com.pppp.travelchecklist.main.viewmodel.MainViewState
import com.pppp.travelchecklist.main.viewmodel.SettingsUseCase
import com.pppp.travelchecklist.main.viewmodel.SharedPreferencesSettingsUseCase
import com.pppp.travelchecklist.navigation.BottomNavigationDrawerFragment
import com.pppp.travelchecklist.navigation.MenuCreator
import com.pppp.travelchecklist.navigation.MenuCreatorImpl
import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import com.pppp.travelchecklist.utils.UtilsModule
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import io.mockk.mockk
import javax.inject.Singleton

@Component(modules = arrayOf(MockAppModule::class, UtilsModule::class))
interface MockAppComponent : AppComponent {
    fun with(module: MockMainModule): MockMainSubComponent
}

@Module
class MockAppModule(context: Context) : AppModule(context, FirebaseAnalytics.getInstance(context)){
    @Provides
    fun provideProducer(): ViewStatesProducer<MainViewState> = mockk(relaxed = true)
}

@Singleton
@Subcomponent(modules = arrayOf(MockMainModule::class))
interface MockMainSubComponent : MainSubComponent {

}

@Module
class MockMainModule {

    @Provides
    fun provideMainViewModel(): MainViewModel = mockk(relaxed = true)

    @Provides
    fun provideConsumer(): ViewActionsConsumer<MainViewIntent> = mockk(relaxed = true)

    @Provides
    fun provideTransientEvdents(): TransientEventsProducer<MainTransientEvent> = mockk(relaxed = true)

    @Provides
    fun provideMenuCreator(): MenuCreator = mockk(relaxed = true)

    @Provides
    fun provideNavigationActionMapper(): Navigator = mockk(relaxed = true)

    @Provides
    fun provideTitleUseCase(): TitleUseCase = mockk(relaxed = true)

    @Provides
    fun provideSettingsUseCase(): SettingsUseCase = mockk(relaxed = true)
}
