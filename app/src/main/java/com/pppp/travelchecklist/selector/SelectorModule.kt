package com.pppp.travelchecklist.selector

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.pppp.travelchecklist.selector.presenter.SelectionData
import com.pppp.travelchecklist.selector.presenter.SelectorPresenter
import com.pppp.travelchecklist.selector.view.viewpager.mappers.*
import com.pppp.travelchecklist.utils.ResourcesWrapper
import dagger.Module
import dagger.Provides

@Module
class SelectorModule(private val activity: FragmentActivity) {

    @Provides
    fun provideSelectorPresenter(factory: SelectorPresenterFactory) =
        ViewModelProviders.of(activity, factory).get(SelectorPresenter::class.java)

    @Provides
    fun provideTripLengthMapper(wrapper: ResourcesWrapper) = TripLengthMapper(wrapper)

    @Provides
    fun provideWeatherMapper(wrapper: ResourcesWrapper) = WeatherMapper(wrapper)

    @Provides
    fun provideAccomodationMapper() = AccomodationMapper()

    @Provides
    fun providePlannedActivitiesMapper() = PlannedActivitiesMapper()

    @Provides
    fun provideWhoIsTravellingMapper(wrapper: ResourcesWrapper) = WhoIsTravellingMapper(wrapper)

    @Provides
    fun provideFactory(wrapper: ResourcesWrapper) = SelectorPresenterFactory(wrapper)

    class SelectorPresenterFactory(val resourcesWrapper: ResourcesWrapper) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            SelectorPresenter(SelectionData(), resourcesWrapper) as T
    }
}