package com.pppp.travelchecklist.createlist.di

import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.createlist.model.models.InitialTagsRepository
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.createlist.NewListActivity
import com.pppp.travelchecklist.createlist.model.TagsCache
import com.pppp.travelchecklist.createlist.model.TagsCacheFactory
import com.pppp.travelchecklist.createlist.model.TagsCacheImpl
import com.pppp.travelchecklist.createlist.presenter.NewListPresenter
import com.pppp.travelchecklist.createlist.presenter.SelectorPresenterFactory
import com.pppp.travelchecklist.createlist.model.models.*
import com.pppp.travelchecklist.utils.ResourcesWrapper
import dagger.Module
import dagger.Provides

@Module
class NewListModule(private val newListActivity: NewListActivity) {

    @Provides
    fun provideSelectorPresenter(factory: SelectorPresenterFactory) =
        ViewModelProviders.of(newListActivity, factory).get(NewListPresenter::class.java)

    @Provides
    fun providesWhoIsTravellingModel(factory: WhoIsTravellingModelFactory): WhoIsTravellingModel =
        ViewModelProviders.of(newListActivity, factory).get(WhoIsTravellingModel::class.java)

    @Provides
    fun provideWhoIsTravellingModelFactory(db: TagsCache) =
        WhoIsTravellingModelFactory(db)

    @Provides
    fun providesPlannedActivitesModel(factory: PlannedActivitesModelFactory): PlannedActivitesModel =
        ViewModelProviders.of(newListActivity, factory).get(PlannedActivitesModel::class.java)

    @Provides
    fun providePlannedActivitesModelFactory(db: TagsCache) =
        PlannedActivitesModelFactory(db)

    @Provides
    fun provideSelectorPresenterFactory(wrapper: ResourcesWrapper, listGenerator: ListGenerator) =
        SelectorPresenterFactory(wrapper, listGenerator)

    @Provides
    fun providesExpectedWeatherModel(factory: ExpectedWeatherModelFactory): ExpectedWeatherModel =
        ViewModelProviders.of(newListActivity, factory).get(ExpectedWeatherModel::class.java)

    @Provides
    fun provideExpectedWeatherModelFactory(db: TagsCache) =
        ExpectedWeatherModelFactory(db)

    @Provides
    fun providesAccomodationModel(factory: AccomodationModelFactory): AccomodationModel =
        ViewModelProviders.of(newListActivity, factory).get(AccomodationModel::class.java)

    @Provides
    fun provideAccomodationModelFactory(db: TagsCache) =
        AccomodationModelFactory(db)

    @Provides
    fun providesLongOrShortTripModel(factory: LongOrShortTripModelFactory): LongOrShortTripModel =
        ViewModelProviders.of(newListActivity, factory).get(LongOrShortTripModel::class.java)

    @Provides
    fun provideLongOrShortTripFragmentFactory(db: TagsCache) =
        LongOrShortTripModelFactory(db)

    @Provides
    fun provideTagsCache(repository: InitialTagsRepository): TagsCache = ViewModelProviders.of(
        newListActivity,
        TagsCacheFactory(repository)
    )[TagsCacheImpl::class.java]

}