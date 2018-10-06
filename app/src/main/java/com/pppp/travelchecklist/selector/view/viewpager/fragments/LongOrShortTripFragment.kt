package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import com.pppp.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment

class LongOrShortTripFragment : ButtonsStripGroupListenerFragment() {

    override fun getTitle() = resources.getString(R.string.long_or_short)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = component.longOrShortTripModel()
    }

    override fun onItemSelected(item: Tag) {
        super.onItemSelected(item)
        callback.onDurationSelected(item)
    }

}