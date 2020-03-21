package com.pppp.travelchecklist.main

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.application.di.AppComponent
import com.pppp.travelchecklist.application.di.AppModule
import com.pppp.travelchecklist.createlist.di.NewListSubComponent
import com.pppp.travelchecklist.edititem.di.EditItemComponent
import com.pppp.travelchecklist.edititem.di.EditItemModule
import com.pppp.travelchecklist.list.di.CheckListFragmentComponent
import com.pppp.travelchecklist.list.di.CheckListFragmentModule
import com.pppp.travelchecklist.list.di.ViewCheckListComponent
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.list.viewmodel.TitleUseCase
import com.pppp.travelchecklist.login.di.LoginComponent
import com.pppp.travelchecklist.login.di.LoginModule
import com.pppp.travelchecklist.main.di.MainSubComponent
import com.pppp.travelchecklist.main.model.Navigator
import com.pppp.travelchecklist.main.viewmodel.MainViewModel
import com.pppp.travelchecklist.main.viewmodel.SettingsUseCase
import com.pppp.travelchecklist.navigation.MenuCreator
import com.pppp.travelchecklist.notifications.di.NotificationModule
import com.pppp.travelchecklist.notifications.di.NotificationSubComponent
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
    abstract fun with(module: LoginModule): LoginComponent
    abstract fun with(module: ViewCheckListModule): ViewCheckListComponent
    abstract fun with(module: EditItemModule): EditItemComponent
    abstract fun with(module: NotificationModule): NotificationSubComponent
    abstract fun with(module: CheckListFragmentModule): CheckListFragmentComponent
    abstract fun inject(app: App)
    abstract fun mainSubComponentFactory(): MainSubComponent.Factory
    abstract fun newListSubComponentFactory(): NewListSubComponent.Factory
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
