package com.pppp.travelchecklist.selector

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.pppp.travelchecklist.selector.presenter.SelectorPresenter
import com.pppp.travelchecklist.selector.view.viewpager.mappers.*
import com.pppp.travelchecklist.utils.ResourcesWrapper
import dagger.Module
import dagger.Provides

@Module
class SelectorModule(private val activity: FragmentActivity) {

    @Provides
    fun provideSelectorPresenter() =
        ViewModelProviders.of(activity).get(SelectorPresenter::class.java)

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
}