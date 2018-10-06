package com.pppp.travelchecklist.selector.view.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.pppp.travelchecklist.selector.view.viewpager.fragments.*

class SelectionViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> WhoIsTravellingFragment()
            1 -> DestinationFragment()
            2 -> ExpectedWeatherFragment()
            3 -> AccomodationFragment()
            4 -> PlannedActivitiesFragment()
            5 -> LongOrShortTripFragment()
            else -> throw UnsupportedOperationException("Invalid position")
        }

    override fun getCount() = 6

}