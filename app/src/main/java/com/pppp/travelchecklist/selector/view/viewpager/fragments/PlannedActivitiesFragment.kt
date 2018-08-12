package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import kotlinx.android.synthetic.main.planned_acctivites.*


class PlannedActivitiesFragment : ItemSelectorFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.planned_acctivites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = resources.getString(R.string.planned_activities)
        strip.callback = this
        strip.setItems(getItems())
    }

    override fun getItems(): List<ButtonsStrip.Item> {
        return listOf(
            ButtonsStrip.Item(resources.getString(R.string.backpacking)),
            ButtonsStrip.Item(resources.getString(R.string.fishing)),
            ButtonsStrip.Item(resources.getString(R.string.diving)),
            ButtonsStrip.Item(resources.getString(R.string.beach)),
            ButtonsStrip.Item(resources.getString(R.string.work)),
            ButtonsStrip.Item(resources.getString(R.string.goingout)),
            ButtonsStrip.Item(resources.getString(R.string.hiking)),
            ButtonsStrip.Item(resources.getString(R.string.skiing))
        )
    }

    override fun onItemSelected(item: ButtonsStrip.Item) {

    }


    companion object {
        fun newInstance() = PlannedActivitiesFragment()
    }
}