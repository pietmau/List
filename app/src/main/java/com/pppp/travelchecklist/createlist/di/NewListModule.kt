package com.pppp.travelchecklist.createlist.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.pppp.travelchecklist.createlist.model.models.InitialTagsRepository
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.createlist.model.TagsCache
import com.pppp.travelchecklist.createlist.model.TagsCacheFactory
import com.pppp.travelchecklist.createlist.model.TagsCacheImpl
import com.pppp.travelchecklist.createlist.presenter.NewListViewModel
import com.pppp.travelchecklist.createlist.presenter.SelectorPresenterFactory
import com.pppp.travelchecklist.createlist.model.models.*
import com.pppp.travelchecklist.utils.ResourcesWrapper
import dagger.Module
import dagger.Provides

@Module
object NewListModule {

    @JvmStatic
    @Provides
    fun provideSelectorPresenter(factory: SelectorPresenterFactory, newListActivity: FragmentActivity) =
        ViewModelProviders.of(newListActivity, factory).get(NewListViewModel::class.java)

    @JvmStatic
    @Provides
    fun providesWhoIsTravellingModel(factory: WhoIsTravellingModelFactory, newListActivity: FragmentActivity): WhoIsTravellingModel =
        ViewModelProviders.of(newListActivity, factory).get(WhoIsTravellingModel::class.java)

    @JvmStatic
    @Provides
    fun provideWhoIsTravellingModelFactory(db: TagsCache, activity: FragmentActivity) =
        WhoIsTravellingModelFactory(db, activity)

    @JvmStatic
    @Provides
    fun providesPlannedActivitesModel(factory: PlannedActivitesModelFactory, newListActivity: FragmentActivity): PlannedActivitesModel =
        ViewModelProviders.of(newListActivity, factory).get(PlannedActivitesModel::class.java)

    @JvmStatic
    @Provides
    fun providePlannedActivitesModelFactory(db: TagsCache, activity: FragmentActivity) =
        PlannedActivitesModelFactory(db, activity)

    @JvmStatic
    @Provides
    fun provideSelectorPresenterFactory(wrapper: ResourcesWrapper, listGenerator: ListGenerator) =
        SelectorPresenterFactory(wrapper, listGenerator)

    @JvmStatic
    @Provides
    fun providesExpectedWeatherModel(factory: ExpectedWeatherModelFactory, newListActivity: FragmentActivity): ExpectedWeatherModel =
        ViewModelProviders.of(newListActivity, factory).get(ExpectedWeatherModel::class.java)

    @JvmStatic
    @Provides
    fun provideExpectedWeatherModelFactory(db: TagsCache, activity: FragmentActivity) =
        ExpectedWeatherModelFactory(db, activity)

    @JvmStatic
    @Provides
    fun providesAccomodationModel(factory: AccomodationModelFactory, newListActivity: FragmentActivity): AccomodationModel =
        ViewModelProviders.of(newListActivity, factory).get(AccomodationModel::class.java)

    @JvmStatic
    @Provides
    fun provideAccomodationModelFactory(db: TagsCache, activity: FragmentActivity) =
        AccomodationModelFactory(db, activity)

    @JvmStatic
    @Provides
    fun providesLongOrShortTripModel(factory: LongOrShortTripModelFactory, newListActivity: FragmentActivity): LongOrShortTripModel =
        ViewModelProviders.of(newListActivity, factory).get(LongOrShortTripModel::class.java)

    @JvmStatic
    @Provides
    fun provideLongOrShortTripFragmentFactory(db: TagsCache, activity: FragmentActivity) =
        LongOrShortTripModelFactory(db, activity)

    @JvmStatic
    @Provides
    fun provideTagsCache(repository: InitialTagsRepository, newListActivity: FragmentActivity): TagsCache = ViewModelProviders.of(
        newListActivity,
        TagsCacheFactory(repository)
    )[TagsCacheImpl::class.java]

}