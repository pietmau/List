package com.pppp.travelchecklist.createlist.view.viewpager.fragments

import android.os.Bundle
import com.pppp.entities.pokos.RoomTag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.createlist.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment

class LongOrShortTripFragment : ButtonsStripGroupListenerFragment() {

    override fun getTitle() = resources.getString(R.string.long_or_short)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = component.longOrShortTripModel()
    }

    override fun onItemSelected(item: RoomTag) {
        super.onItemSelected(item)
        callback?.onDurationSelected(item)
    }

    override fun onItemDeselected(item: RoomTag) {
        super.onItemDeselected(item)
        callback?.onDurationDeselected(item)
    }
}