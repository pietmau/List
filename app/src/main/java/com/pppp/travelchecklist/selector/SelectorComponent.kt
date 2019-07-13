package com.pppp.travelchecklist.selector

import com.pppp.travelchecklist.selector.view.SelectorFragment
import com.pppp.travelchecklist.selector.view.custom.SelectorView
import com.pppp.travelchecklist.selector.view.viewpager.fragments.*
import com.pppp.travelchecklist.selector.model.models.*
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(SelectorModule::class))
interface SelectorComponent {

    fun inject(selectorView: SelectorView)

    fun inject(selectorView: SelectorFragment)

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

}