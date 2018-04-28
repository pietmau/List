package com.pppp.travelchecklist.selector.view.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.Selection


class LongOrShortTripFragment : Fragment() {

    companion object {
        fun newInstance() = LongOrShortTripFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.long_or_short, container, false)
    }

    fun getSelection(): Selection.SelectionItem.LongOrShortTripSelectionItem? {
        TODO("not implemented")
    }
}