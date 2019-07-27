package com.pppp.travelchecklist.newlist

import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.newlist.model.models.InitialTagsRepository
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.newlist.presenter.NewListPresenter
import com.pppp.travelchecklist.newlist.presenter.SelectorPresenterFactory
import com.pppp.travelchecklist.newlist.model.models.*
import com.pppp.travelchecklist.utils.ResourcesWrapper
import dagger.Module
import dagger.Provides

@Module
class NewListModule(private val activity: androidx.fragment.app.FragmentActivity) {

    @Provides
    fun provideSelectorPresenter(factory: SelectorPresenterFactory) =
        ViewModelProviders.of(activity, factory).get(NewListPresenter::class.java)

    @Provides
    fun providesWhoIsTravellingModel(factory: WhoIsTravellingModelFactory): WhoIsTravellingModel =
        ViewModelProviders.of(activity, factory).get(WhoIsTravellingModel::class.java)

    @Provides
    fun provideWhoIsTravellingModelFactory(db: InitialTagsRepository) =
        WhoIsTravellingModelFactory(db)

    @Provides
    fun providesPlannedActivitesModel(factory: PlannedActivitesModelFactory): PlannedActivitesModel =
        ViewModelProviders.of(activity, factory).get(PlannedActivitesModel::class.java)

    @Provides
    fun providePlannedActivitesModelFactory(db: InitialTagsRepository) =
        PlannedActivitesModelFactory(db)

    @Provides
    fun provideSelectorPresenterFactory(wrapper: ResourcesWrapper, listGenerator: ListGenerator) =
        SelectorPresenterFactory(wrapper, listGenerator)

    @Provides
    fun providesExpectedWeatherModel(factory: ExpectedWeatherModelFactory): ExpectedWeatherModel =
        ViewModelProviders.of(activity, factory).get(ExpectedWeatherModel::class.java)

    @Provides
    fun provideExpectedWeatherModelFactory(db: InitialTagsRepository) =
        ExpectedWeatherModelFactory(db)

    @Provides
    fun providesAccomodationModel(factory: AccomodationModelFactory): AccomodationModel =
        ViewModelProviders.of(activity, factory).get(AccomodationModel::class.java)

    @Provides
    fun provideAccomodationModelFactory(db: InitialTagsRepository) =
        AccomodationModelFactory(db)

    @Provides
    fun providesLongOrShortTripModel(factory: LongOrShortTripModelFactory): LongOrShortTripModel =
        ViewModelProviders.of(activity, factory).get(LongOrShortTripModel::class.java)

    @Provides
    fun provideLongOrShortTripFragmentFactory(db: InitialTagsRepository) =
        LongOrShortTripModelFactory(db)

}