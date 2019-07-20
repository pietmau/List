package com.pppp.travelchecklist.selector.view.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.pppp.travelchecklist.selector.view.viewpager.fragments.*

class SelectionViewPagerAdapter(fragmentManager: androidx.fragment.app.FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment =
        when (position) {
            0 -> WhoIsTravellingFragment()
            1 -> DestinationFragment()
            2 -> ExpectedWeatherFragment()
            3 -> AccomodationFragment()
            4 -> PlannedActivitiesFragment()
            5 -> LongOrShortTripFragment()
            else -> throw UnsupportedOperationException("Invalid itemPosition")
        }

    override fun getCount() = 6

}