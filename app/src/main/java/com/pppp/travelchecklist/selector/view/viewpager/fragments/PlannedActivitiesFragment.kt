package com.pppp.travelchecklist.selector.view.viewpager.fragments


import android.os.Bundle
import com.pietrantuono.entities.Tag
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.viewpager.fragments.models.TagSelectorModel
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ItemSelectorFragment
import kotlinx.android.synthetic.main.item_selector_fragment.*

class PlannedActivitiesFragment : ItemSelectorFragment() {
    override lateinit var model: TagSelectorModel

    override fun getTitle() = resources.getString(R.string.planned_activities)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        model = component.plannedActivitesModel()
    }

    override fun setItems(group: List<Pair<Tag, Boolean>>) {
        showProgress(false)
        strip.setItems(group.map { it.first })
        strip.setItemsSelected(group)
    }

    override fun onItemSelected(item: Tag) {
        super.onItemSelected(item)
        callback.onPlannedActivitySelected(item)
    }

    override fun onItemDeSelected(item: Tag) {
        super.onItemDeSelected(item)
        callback.onPlannedActivityDeselected(item)
    }
}