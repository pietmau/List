package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment

class ExpectedWeatherFragment : ButtonsStripGroupListenerFragment() {

    override fun getTitle() = resources.getString(R.string.expected_weather)

    override fun getLayout() = R.layout.expected_weather

    override fun getItems() = TODO("remove")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = component.expectedWeatherModel()
    }

    override fun onItemSelected(item: ButtonsStrip.Item) {
        //callback.onWeatherSelected(mapper.map(item))
    }

    companion object {
        fun newInstance() = ExpectedWeatherFragment()
    }
}