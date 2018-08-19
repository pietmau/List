package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses.ItemSelectorFragment
import kotlinx.android.synthetic.main.who_is_travelling.*

class WhoIsTravellingFragment : ItemSelectorFragment() {

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

    override fun getItems() = listOf(
        ButtonsStrip.Item(resources.getString(R.string.male)),
        ButtonsStrip.Item(resources.getString(R.string.fermale)),
        ButtonsStrip.Item(resources.getString(R.string.babies)),
        ButtonsStrip.Item(resources.getString(R.string.toddlers))
    )

    override fun onItemSelected(item: ButtonsStrip.Item) {

    }

    companion object {
        fun newInstance() = WhoIsTravellingFragment()
    }
}