package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ItemSelectorFragment
import kotlinx.android.synthetic.main.who_is_travelling.*

class WhoIsTravellingFragment : ItemSelectorFragment() {
    override lateinit var model: TagSelectorModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.who_is_travelling, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = resources.getString(R.string.who_is_travelling)
        strip.callback = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        model = component.whoIsTravellingModel()
    }

    override fun setItems(group: List<Pair<Tag, Boolean>>) {
        strip.setItems(group.map { it.first })
        strip.setItemsSelected(group)
    }

    override fun onItemSelected(tag: Tag?) {
        tag ?: return
        model.onTagSelected(tag)
        callback.onWhoisTravellingSelected(tag)
    }

    override fun onItemDeSelected(item: Tag?) {
        item ?: return
        model.onTagDeSeleected(item)
        callback.onWhoisTravellingDeSelected(item)
    }

    companion object {
        fun newInstance() = WhoIsTravellingFragment()
    }
}