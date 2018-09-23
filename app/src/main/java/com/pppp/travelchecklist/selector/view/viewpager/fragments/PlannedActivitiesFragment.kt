package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ItemSelectorFragment
import com.pppp.travelchecklist.selector.view.viewpager.mappers.PlannedActivitiesMapper
import kotlinx.android.synthetic.main.planned_acctivites.*
import javax.inject.Inject

class PlannedActivitiesFragment : ItemSelectorFragment() {
    @Inject
    lateinit var mapper: PlannedActivitiesMapper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.planned_acctivites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = resources.getString(R.string.planned_activities)
        strip.callback = this
        //strip.setItems(getItems())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun getItems() = TODO()
        /*listOf(
        ButtonsStrip.Item(resources.getString(R.string.backpacking)),
        ButtonsStrip.Item(resources.getString(R.string.fishing)),
        ButtonsStrip.Item(resources.getString(R.string.diving)),
        ButtonsStrip.Item(resources.getString(R.string.beach)),
        ButtonsStrip.Item(resources.getString(R.string.work)),
        ButtonsStrip.Item(resources.getString(R.string.goingout)),
        ButtonsStrip.Item(resources.getString(R.string.hiking)),
        ButtonsStrip.Item(resources.getString(R.string.skiing))
    )*/

    override fun onItemSelected(item: ButtonsStrip.Item) {
        callback.onPlannedActivitySelected(mapper.map(item))
    }

    override fun onItemDeSelected(item: ButtonsStrip.Item) {
        callback.onPlannedActivityDeselected(mapper.map(item))
    }

    companion object {
        fun newInstance() = PlannedActivitiesFragment()
    }
}