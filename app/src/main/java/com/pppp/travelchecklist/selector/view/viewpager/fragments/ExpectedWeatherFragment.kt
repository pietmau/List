package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ButtonsStripGroupListenerFragment
import com.pppp.travelchecklist.selector.view.viewpager.mappers.WeatherMapper
import kotlinx.android.synthetic.main.expected_weather.*
import javax.inject.Inject

class ExpectedWeatherFragment : ButtonsStripGroupListenerFragment() {
    @Inject
    lateinit var mapper: WeatherMapper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = resources.getString(R.string.expected_weather)
        strip.listener = this
        strip.setItems(getItems())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.expected_weather, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun getItems() = listOf(
        ButtonsStrip.Item(resources.getString(R.string.hot)),
        ButtonsStrip.Item(resources.getString(R.string.cold))
    )

    override fun onItemSelected(item: ButtonsStrip.Item) {
        callback.onWeatherSelected(mapper.map(item))
    }

    companion object {
        fun newInstance() = ExpectedWeatherFragment()
    }
}