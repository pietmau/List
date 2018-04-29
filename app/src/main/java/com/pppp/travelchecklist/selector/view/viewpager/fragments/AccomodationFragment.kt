package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.model.Selection


class AccomodationFragment : Fragment() {

    companion object {
        fun newInstance() =
            AccomodationFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.accomodation, container, false)
    }

    fun getSelection() = Selection.SelectionItem.AccommodationSelectionItem.Hotel()
}