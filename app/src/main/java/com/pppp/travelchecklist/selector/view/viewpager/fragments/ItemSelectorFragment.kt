package com.pppp.travelchecklist.selector.view.viewpager.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip

abstract class ItemSelectorFragment : Fragment(), ButtonsStrip.Callback {
    override abstract fun onViewCreated(view: View, savedInstanceState: Bundle?)

    override abstract fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    abstract fun getItems(): List<ButtonsStrip.Item>

    override abstract fun onItemSelected(item: ButtonsStrip.Item)
}