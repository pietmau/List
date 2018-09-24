package com.pppp.travelchecklist.selector

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.pppp.database.CheckListDatabase
import com.pppp.travelchecklist.listgenerator.ListGenerator
import com.pppp.travelchecklist.selector.presenter.SelectionData
import com.pppp.travelchecklist.selector.presenter.SelectorPresenter
import com.pppp.travelchecklist.selector.view.viewpager.fragments.PlannedActivitesModel
import com.pppp.travelchecklist.selector.view.viewpager.fragments.WhoIsTravellingModel
import com.pppp.travelchecklist.selector.view.viewpager.mappers.*
import com.pppp.travelchecklist.utils.ResourcesWrapper
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
    fun provideWhoIsTravellingModelFactory(db: CheckListDatabase) = WhoIsTravellingModelFactory(db)

    @Provides
    fun providesPlannedActivitesModel(factory: PlannedActivitesModelFactory): PlannedActivitesModel =
        ViewModelProviders.of(activity, factory).get(PlannedActivitesModel::class.java)

    @Provides
    fun providePlannedActivitesModelFactory(db: CheckListDatabase) = PlannedActivitesModelFactory(db)

    @Provides
    fun provideSelectorPresenterFactory(
        wrapper: ResourcesWrapper,
        listGenerator: ListGenerator
    ) = SelectorPresenterFactory(wrapper, listGenerator)

    class SelectorPresenterFactory(
        val resourcesWrapper: ResourcesWrapper,
        val listGenerator: ListGenerator
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            SelectorPresenter(
                SelectionData(),
                resourcesWrapper,
                listGenerator,
                AndroidSchedulers.mainThread(),
                Schedulers.io()
            ) as T
    }

    class WhoIsTravellingModelFactory(val db: CheckListDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            WhoIsTravellingModel(db, "who is travelling? ✈️") as T
    }

    class PlannedActivitesModelFactory(val db: CheckListDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            PlannedActivitesModel(db, "planned activities") as T
    }
}