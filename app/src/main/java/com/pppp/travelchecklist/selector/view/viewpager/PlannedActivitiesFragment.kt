package com.pppp.travelchecklist.selector.view.viewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.Selection


class PlannedActivitiesFragment : Fragment() {

    companion object {
        fun newInstance() = PlannedActivitiesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.planned_acctivites, container, false)
    }

    fun getSelection(): List<Selection.SelectionItem.PlannedActivitiesSelectionItem> {
        TODO("not implemented")
    }
}