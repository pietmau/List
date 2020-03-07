package com.pppp.travelchecklist.main

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.application.di.AppComponent
import com.pppp.travelchecklist.application.di.AppModule
import com.pppp.travelchecklist.list.viewmodel.TitleUseCase
import com.pppp.travelchecklist.main.di.MainSubComponent
import com.pppp.travelchecklist.main.model.Navigator
import com.pppp.travelchecklist.main.viewmodel.MainViewModel
import com.pppp.travelchecklist.main.viewmodel.SettingsUseCase
import com.pppp.travelchecklist.navigation.MenuCreator
import com.pppp.travelchecklist.utils.UtilsModule
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import io.mockk.mockk
import javax.inject.Singleton

@Component(modules = [MockAppModule::class, UtilsModule::class])
abstract class MockAppComponent : AppComponent {

    abstract fun with(module: MockMainModule): MockMainSubComponent
}

@Module
class MockAppModule(context: Context) : AppModule(context, FirebaseAnalytics.getInstance(context)) {

}

@Singleton
@Subcomponent(modules = arrayOf(MockMainModule::class))
interface MockMainSubComponent : MainSubComponent

@Module
class MockMainModule {
    @Provides
    fun provideProducer(): ViewStatesProducer<MainViewState> = mockk(relaxed = true)

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
