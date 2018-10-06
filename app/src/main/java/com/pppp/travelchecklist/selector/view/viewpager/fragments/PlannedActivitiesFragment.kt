package com.pppp.travelchecklist.selector.view.viewpager.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.viewpager.fragments.models.TagSelectorModel
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ItemSelectorFragment
import kotlinx.android.synthetic.main.planned_acctivites.*

class PlannedActivitiesFragment : ItemSelectorFragment() {

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

    override fun setItems(group: List<Pair<Tag, Boolean>>) {
        strip.setItems(group.map { it.first })
        strip.setItemsSelected(group)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        model = component.plannedActivitesModel()
    }

    override fun onItemSelected(item: Tag) {
        super.onItemSelected(item)
        callback.onPlannedActivitySelected(item)
    }

    override fun onItemDeSelected(item: Tag) {
        super.onItemDeSelected(item)
        callback.onPlannedActivityDeselected(item)
    }

    companion object {
        fun newInstance() = PlannedActivitiesFragment()
    }
}