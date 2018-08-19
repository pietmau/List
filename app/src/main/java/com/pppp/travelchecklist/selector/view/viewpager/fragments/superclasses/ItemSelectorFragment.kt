package com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.selector.SelectorComponent
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.selector.view.SelectorCallback
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip

abstract class ItemSelectorFragment : Fragment(), ButtonsStrip.Callback {
    protected val callback
        get() = activity as? SelectorCallback

    protected lateinit var component: SelectorComponent

    override abstract fun onViewCreated(view: View, savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (requireActivity().applicationContext as App).appComponent
        component = appComponent.with(SelectorModule(requireActivity()))
    }

    override abstract fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    protected abstract fun getItems(): List<ButtonsStrip.Item>

    override abstract fun onItemSelected(item: ButtonsStrip.Item)

    override fun onItemDeSelected(item: ButtonsStrip.Item) {/*NoOp*/
    }
}