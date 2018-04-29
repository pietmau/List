package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.model.Selection


class PlannedActivitiesFragment : Fragment() {

    companion object {
        fun newInstance() =
            PlannedActivitiesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.planned_acctivites, container, false)
        return view
    }

    fun getSelection() = mutableListOf<Selection.SelectionItem.PlannedActivitiesSelectionItem>()
}