package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment

class LongOrShortTripFragment : ButtonsStripGroupListenerFragment() {
    override fun getLayout() = R.layout.long_or_short

    override fun getTitle() = resources.getString(R.string.long_or_short)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun getItems() = TODO()

    override fun onItemSelected(item: ButtonsStrip.Item) {
        //callback.onDurationSelected(mapper.map(item))
    }

    companion object {
        fun newInstance() = LongOrShortTripFragment()
    }
}