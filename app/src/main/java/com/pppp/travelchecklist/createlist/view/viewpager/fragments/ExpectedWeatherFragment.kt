package com.pppp.travelchecklist.createlist.view.viewpager.fragments

import android.os.Bundle
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.createlist.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment

class ExpectedWeatherFragment : ButtonsStripGroupListenerFragment() {
    override fun getTitle() = resources.getString(R.string.expected_weather)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = subComponent.expectedWeatherModel()
    }

    override fun onItemSelected(item: TagImpl) {
        super.onItemSelected(item)
        callback?.onWeatherSelected(item)
    }

    override fun onItemDeselected(item: TagImpl) {
        super.onItemDeselected(item)
        callback?.onWeatherDeselected(item)
    }
}