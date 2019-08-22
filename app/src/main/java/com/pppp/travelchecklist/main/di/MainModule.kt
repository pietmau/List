package com.pppp.travelchecklist.main.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.Consumer
import com.pppp.travelchecklist.Producer
import com.pppp.travelchecklist.main.model.MainModelImpl
import com.pppp.travelchecklist.main.viewmodel.MainViewModel
import com.pppp.travelchecklist.TransientEvents
import com.pppp.travelchecklist.main.model.Navigator
import com.pppp.travelchecklist.main.model.NavigatorImpl
import com.pppp.travelchecklist.main.view.MenuCreator
import com.pppp.travelchecklist.main.view.MenuCreatorImpl
import com.pppp.travelchecklist.repository.TravelChecklistRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule(private val activity: FragmentActivity) {

    @Singleton
    @Provides
    fun providePresenter(repo: TravelChecklistRepository) = ViewModelProviders.of(activity, MainViewModelFactory(repo)).get(MainViewModel::class.java)

    @Provides
    fun provideMenuCreator(creator: MenuCreatorImpl): MenuCreator = creator

    @Provides
    fun provideProducer(viewModel: MainViewModel): Producer<MainViewModel.ViewState> = viewModel

    @Provides
    fun provideConsumer(viewModel: MainViewModel): Consumer<MainViewModel.ViewEvent> = viewModel

    @Provides
    fun provideTransientEvdents(viewModel: MainViewModel): TransientEvents<MainViewModel.TransientEvent> = viewModel

    @Provides
    fun provideNavigationActionMapper(): Navigator = NavigatorImpl

    class MainViewModelFactory(private val repo: TravelChecklistRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(MainModelImpl(repo)) as T
    }
}
