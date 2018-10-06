package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment

class AccomodationFragment : ButtonsStripGroupListenerFragment() {
    override fun getLayout() = R.layout.accomodation

    override fun getTitle() = resources.getString(R.string.accomodation)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun getItems() = TODO()

    override fun onItemSelected(item: ButtonsStrip.Item) {
        //callback.onAccomodationSelected(mapper.map(item))
    }

    companion object {
        fun newInstance() = AccomodationFragment()
    }
}