package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment

class ExpectedWeatherFragment : ButtonsStripGroupListenerFragment() {
    override fun getTitle() = resources.getString(R.string.expected_weather)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = component.expectedWeatherModel()
    }

    override fun onItemSelected(item: TagImpl) {
        super.onItemSelected(item)
        callback?.onWeatherSelected(item)
    }
}