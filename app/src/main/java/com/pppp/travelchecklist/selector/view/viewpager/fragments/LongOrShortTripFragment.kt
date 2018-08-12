package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.selector.view.SelectorCallback
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.custom.ButtonsStripGroup

import kotlinx.android.synthetic.main.long_or_short.*

class LongOrShortTripFragment : Fragment(), ButtonsStripGroup.Listener {
    private val callback
        get() = activity as? SelectorCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.long_or_short, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = resources.getString(R.string.long_or_short)
        strip.listener = this
        strip.setItems(getItems())
    }

    private fun getItems(): List<ButtonsStrip.Item> {
        return listOf(
            ButtonsStrip.Item(resources.getString(R.string.yes)),
            ButtonsStrip.Item(resources.getString(R.string.no))
        )
    }

    override fun onItemSelected(item: ButtonsStrip.Item) {

    }

    override fun onItemDeselected(item: ButtonsStrip.Item) {
    }

    companion object {
        fun newInstance() = LongOrShortTripFragment()
    }
}