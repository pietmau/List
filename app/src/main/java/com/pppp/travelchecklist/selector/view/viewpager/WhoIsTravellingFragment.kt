package com.pppp.travelchecklist.selector.view.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R


class WhoIsTravellingFragment : Fragment() {

    companion object {
        fun newInstance() = WhoIsTravellingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.who_is_travelling, container, false)
    }
}