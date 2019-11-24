package com.pppp.travelchecklist.createlist.di

import com.pppp.travelchecklist.createlist.view.NewListFragment
import com.pppp.travelchecklist.createlist.view.custom.SelectorView
import com.pppp.travelchecklist.createlist.view.viewpager.fragments.*
import com.pppp.travelchecklist.createlist.model.models.*
import com.pppp.travelchecklist.createlist.initialdownload.InitialDownloadFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(NewListModule::class))
interface NewListComponent {

    fun inject(selectorView: SelectorView)

    fun inject(newListView: NewListFragment)

    fun inject(destinationFragment: DestinationFragment)

    fun inject(longOrShortTripFragment: LongOrShortTripFragment)

    fun inject(expectedWeatherFragment: ExpectedWeatherFragment)

    fun inject(accomodationFragment: AccomodationFragment)

    fun inject(plannedActivitiesFragment: PlannedActivitiesFragment)

    fun inject(whoIsTravellingFragment: WhoIsTravellingFragment)

    fun whoIsTravellingModel(): WhoIsTravellingModel

    fun plannedActivitesModel(): PlannedActivitesModel

    fun expectedWeatherModel(): ExpectedWeatherModel

    fun accomodationModel(): AccomodationModel

    fun longOrShortTripModel(): LongOrShortTripModel

    fun inject(initialDownloadFragment: InitialDownloadFragment)

}