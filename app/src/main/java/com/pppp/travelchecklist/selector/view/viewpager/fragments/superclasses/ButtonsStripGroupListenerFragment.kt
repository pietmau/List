package com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.presenter.MainView
import com.pppp.travelchecklist.selector.SelectorComponent
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.custom.ButtonsStripGroup

abstract open class ButtonsStripGroupListenerFragment : Fragment(), ButtonsStripGroup.Listener {
    protected val callback
        get() = (requireActivity() as MainView).selectionCallback

    protected lateinit var component: SelectorComponent

    abstract override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (requireActivity().applicationContext as App).appComponent
        component = appComponent.with(SelectorModule(requireActivity()))
    }

    abstract override fun onViewCreated(view: View, savedInstanceState: Bundle?)

    abstract fun getItems(): List<ButtonsStrip.Item>

    override fun onItemSelected(item: ButtonsStrip.Item) {}

    override fun onItemDeselected(item: ButtonsStrip.Item) {}
}