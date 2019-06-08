package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import com.pietrantuono.entities.Tag
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.viewpager.fragments.models.TagSelectorModel
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ItemSelectorFragment
import kotlinx.android.synthetic.main.item_selector_fragment.*

class WhoIsTravellingFragment : ItemSelectorFragment() {
    override lateinit var model: TagSelectorModel

    override fun getTitle() = resources.getString(R.string.who_is_travelling)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        model = component.whoIsTravellingModel()
    }

    override fun setItems(group: List<Pair<Tag, Boolean>>) {
        showProgress(false)
        strip.setItems(group.map { it.first })
        strip.setItemsSelected(group)
    }

    override fun onItemSelected(tag: TagImpl) {
        super.onItemSelected(tag)
        callback.onWhoisTravellingSelected(tag)
    }

    override fun onItemDeSelected(item: TagImpl) {
        super.onItemDeSelected(item)
        callback.onWhoisTravellingDeSelected(item)
    }
}