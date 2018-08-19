package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.custom.ButtonsStripGroup

abstract open class ButtonsStripGroupListenerFragment : Fragment(), ButtonsStripGroup.Listener {

    abstract override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    abstract override fun onViewCreated(view: View, savedInstanceState: Bundle?)

    abstract fun getItems(): List<ButtonsStrip.Item>

    override fun onItemSelected(item: ButtonsStrip.Item) {}

    override fun onItemDeselected(item: ButtonsStrip.Item) {}
}