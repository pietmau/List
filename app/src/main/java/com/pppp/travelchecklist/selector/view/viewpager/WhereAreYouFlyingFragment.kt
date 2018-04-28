package com.pppp.travelchecklist.selector.view.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R


class WhereAreYouFlyingFragment : Fragment() {

    companion object {
        fun newInstance() = WhereAreYouFlyingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.where_are_you_flying, container, false)
    }
}