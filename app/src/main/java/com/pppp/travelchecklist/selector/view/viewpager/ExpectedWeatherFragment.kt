package com.pppp.travelchecklist.selector.view.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.model.Selection


class ExpectedWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = ExpectedWeatherFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.expected_weather, container, false)
    }

    fun getSelection(): Selection.SelectionItem.ExpectedWeatherSelectionItem? {
        TODO("not implemented")
    }
}