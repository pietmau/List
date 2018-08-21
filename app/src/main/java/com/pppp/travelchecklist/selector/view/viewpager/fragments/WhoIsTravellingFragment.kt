package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ItemSelectorFragment
import com.pppp.travelchecklist.selector.view.viewpager.mappers.WhoIsTravellingMapper
import kotlinx.android.synthetic.main.who_is_travelling.*
import javax.inject.Inject

class WhoIsTravellingFragment : ItemSelectorFragment() {
    @Inject
    lateinit var mapper: WhoIsTravellingMapper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.who_is_travelling, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = resources.getString(R.string.who_is_travelling)
        strip.callback = this
        strip.setItems(getItems())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun getItems() = listOf(
        ButtonsStrip.Item(resources.getString(R.string.male)),
        ButtonsStrip.Item(resources.getString(R.string.fermale)),
        ButtonsStrip.Item(resources.getString(R.string.babies)),
        ButtonsStrip.Item(resources.getString(R.string.kids_toddlers))
    )

    override fun onItemSelected(item: ButtonsStrip.Item) {
        callback.onWhoisTravellingSelected(mapper.map(item))
    }

    override fun onItemDeSelected(item: ButtonsStrip.Item) {
        callback.onWhoisTravellingDeSelected(mapper.map(item))
    }

    companion object {
        fun newInstance() = WhoIsTravellingFragment()
    }
}