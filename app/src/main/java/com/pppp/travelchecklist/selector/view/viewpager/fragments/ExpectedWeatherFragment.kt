package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import kotlinx.android.synthetic.main.expected_weather.*


class ExpectedWeatherFragment : ItemSelectorFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = resources.getString(R.string.expected_weather)
        strip.callback = this
        strip.setItems(getItems())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.expected_weather, container, false)

    override fun getItems(): List<ButtonsStrip.Item> {
        return listOf(
            ButtonsStrip.Item(resources.getString(R.string.hot)),
            ButtonsStrip.Item(resources.getString(R.string.hostel)),
            ButtonsStrip.Item(resources.getString(R.string.cold))
        )
    }

    override fun onItemSelected(item: ButtonsStrip.Item) {

    }

    companion object {
        fun newInstance() = ExpectedWeatherFragment()
    }
}