package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment


class AccomodationFragment : ButtonsStripGroupListenerFragment() {

    override fun getTitle() = resources.getString(R.string.accomodation)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = component.accomodationModel()
    }

    override fun onItemSelected(tag: TagImpl) {
        super.onItemSelected(tag)
        callback?.onAccomodationSelected(tag)
    }
}