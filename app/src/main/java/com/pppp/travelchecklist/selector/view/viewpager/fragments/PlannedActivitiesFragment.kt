package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ItemSelectorFragment
import kotlinx.android.synthetic.main.planned_acctivites.*

class PlannedActivitiesFragment : ItemSelectorFragment() {
    override fun setItems(group: List<Pair<Tag, Boolean>>) {
    }

    override lateinit var model: TagSelectorModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.planned_acctivites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = resources.getString(R.string.planned_activities)
        strip.callback = this

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        model = component.whoIsTravellingModel()
    }

    override fun onItemSelected(item: Tag?) {
        //strip.setItems()
        //callback.onPlannedActivitySelected(mapper.map(item))
    }

    override fun onItemDeSelected(item: Tag?) {
        //callback.onPlannedActivityDeselected(mapper.map(item))
    }

    companion object {
        fun newInstance() = PlannedActivitiesFragment()
    }
}