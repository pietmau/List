package com.pppp.travelchecklist.createlist.view.viewpager.fragments

import android.os.Bundle
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.createlist.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment

class AccomodationFragment : ButtonsStripGroupListenerFragment() {

    override fun getTitle() = resources.getString(R.string.accomodation)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = component.accomodationModel()
    }

    override fun onItemSelected(tag: TagImpl) {
        super.onItemSelected(tag)
        callback?.onAccommodationSelected(tag)
    }

    override fun onItemDeselected(item: TagImpl) {
        super.onItemDeselected(item)
        callback?.onAccommodationDeselected(item)
    }
}