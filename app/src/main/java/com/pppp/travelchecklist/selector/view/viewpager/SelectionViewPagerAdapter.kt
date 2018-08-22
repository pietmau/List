package com.pppp.travelchecklist.selector.view.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.pppp.travelchecklist.selector.view.viewpager.fragments.*

class SelectionViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> WhoIsTravellingFragment.newInstance()
            1 -> DestinationFragment.newInstance()
            2 -> ExpectedWeatherFragment.newInstance()
            3 -> AccomodationFragment.newInstance()
            4 -> PlannedActivitiesFragment.newInstance()
            5 -> LongOrShortTripFragment.newInstance()
            else -> throw UnsupportedOperationException("Invalid position")
        }

    override fun getCount() = 6

}