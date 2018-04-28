package com.pppp.travelchecklist.selector.view.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.pppp.travelchecklist.selector.view.model.Selection

class SelectionViewPagerAdapter(val fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> WhoIsTravellingFragment.newInstance()
            1 -> WhereAreYouFlyingFragment.newInstance()
            2 -> ExpectedWeatherFragment.newInstance()
            3 -> AccomodationFragment.newInstance()
            4 -> PlannedActivitiesFragment.newInstance()
            5 -> LongOrShortTripFragment.newInstance()
            else -> throw UnsupportedOperationException("Invalid position")
        }

    override fun getCount() = 6

    fun getSelection(): Selection {
        val whoIsTravelling = (getItem(0) as WhoIsTravellingFragment).getSelection()
        val wereAreYouFlying = (getItem(1) as WhereAreYouFlyingFragment).getSelection()
        val expectedWeatherSelectionItem = (getItem(2) as ExpectedWeatherFragment).getSelection()
        val accommodationSelectionItem = (getItem(3) as AccomodationFragment).getSelection()
        val plannedActivitiesSelectionItem = (getItem(4) as PlannedActivitiesFragment).getSelection()
        val longOrShortTripSelectionItem = (getItem(4) as LongOrShortTripFragment).getSelection()

        return Selection(whoIsTravelling,
            wereAreYouFlying,
            expectedWeatherSelectionItem,
            accommodationSelectionItem,
            plannedActivitiesSelectionItem,
            longOrShortTripSelectionItem)
    }

}