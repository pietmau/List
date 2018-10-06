package com.pppp.travelchecklist.selector

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.pppp.database.CheckListDatabase
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.selector.presenter.SelectorPresenter
import com.pppp.travelchecklist.selector.presenter.SelectorPresenterFactory
import com.pppp.travelchecklist.selector.view.viewpager.fragments.*
import com.pppp.travelchecklist.utils.ResourcesWrapper
import dagger.Module
import dagger.Provides

@Module
class SelectorModule(private val activity: FragmentActivity) {

    @Provides
    fun provideSelectorPresenter(factory: SelectorPresenterFactory) =
        ViewModelProviders.of(activity, factory).get(SelectorPresenter::class.java)

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
    fun providesExpectedWeatherModel(factory: ExpectedWeatherModelFactory): ExpectedWeatherModel =
        ViewModelProviders.of(activity, factory).get(ExpectedWeatherModel::class.java)

    @Provides
    fun provideExpectedWeatherModelFactory(db: CheckListDatabase) =
        ExpectedWeatherModelFactory(db)

    @Provides
    fun providesAccomodationModel(factory: AccomodationModelFactory): AccomodationModel =
        ViewModelProviders.of(activity, factory).get(AccomodationModel::class.java)

    @Provides
    fun provideAccomodationModelFactory(db: CheckListDatabase) =
        AccomodationModelFactory(db)

    @Provides
    fun providesLongOrShortTripModel(factory: LongOrShortTripModelFactory): LongOrShortTripModel =
        ViewModelProviders.of(activity, factory).get(LongOrShortTripModel::class.java)

    @Provides
    fun provideLongOrShortTripFragmentFactory(db: CheckListDatabase) =
        LongOrShortTripModelFactory(db)

}