package com.pppp.travelchecklist.createlist.view.viewpager.fragments

import android.os.Bundle
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.createlist.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment

class LongOrShortTripFragment : ButtonsStripGroupListenerFragment() {

    override fun getTitle() = resources.getString(R.string.long_or_short)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = subComponent.longOrShortTripModel()
    }

    override fun onItemSelected(item: TagImpl) {
        super.onItemSelected(item)
        callback?.onDurationSelected(item)
    }

    override fun onItemDeselected(item: TagImpl) {
        super.onItemDeselected(item)
        callback?.onDurationDeselected(item)
    }
}