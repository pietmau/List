package com.pppp.travelchecklist.selector

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.pppp.database.CheckListDatabase
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.selector.presenter.SelectorPresenter
import com.pppp.travelchecklist.selector.view.viewpager.fragments.ExpectedWeatherModel
import com.pppp.travelchecklist.selector.view.viewpager.fragments.PlannedActivitesModel
import com.pppp.travelchecklist.selector.view.viewpager.fragments.WhoIsTravellingModel
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
    fun providesWhoIsTravellingModel(factory: WhoIsTravellingModelFactory): WhoIsTravellingModel =
        ViewModelProviders.of(activity, factory).get(WhoIsTravellingModel::class.java)

    @Provides
    fun provideWhoIsTravellingModelFactory(db: CheckListDatabase) =
        WhoIsTravellingModelFactory(db)

    @Provides
    fun providesPlannedActivitesModel(factory: PlannedActivitesModelFactory): PlannedActivitesModel =
        ViewModelProviders.of(activity, factory).get(PlannedActivitesModel::class.java)

    @Provides
    fun providePlannedActivitesModelFactory(db: CheckListDatabase) =
        PlannedActivitesModelFactory(db)

    @Provides
    fun provideSelectorPresenterFactory(wrapper: ResourcesWrapper, listGenerator: ListGenerator) =
        SelectorPresenterFactory(wrapper, listGenerator)

    @Provides
    fun providesExpectedWeatherModel(factory: PlannedActivitesModelFactory): ExpectedWeatherModel =
        ViewModelProviders.of(activity, factory).get(ExpectedWeatherModel::class.java)

    @Provides
    fun provideExpectedWeatherModelFactory(db: CheckListDatabase) = ExpectedWeatherModelFactory(db)

}