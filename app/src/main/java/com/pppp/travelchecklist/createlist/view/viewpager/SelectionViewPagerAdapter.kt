package com.pppp.travelchecklist.createlist.view.viewpager

import androidx.fragment.app.FragmentPagerAdapter
import com.pppp.travelchecklist.createlist.view.viewpager.fragments.*

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
            6 -> NameFragment()
            else -> throw UnsupportedOperationException("Invalid itemId")
        }

    override fun getCount() = 7

}