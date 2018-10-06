package com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.presenter.MainView
import com.pppp.travelchecklist.selector.SelectorComponent
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.custom.ButtonsStripGroup
import com.pppp.travelchecklist.selector.view.viewpager.fragments.models.TagSelectorModel
import io.reactivex.disposables.CompositeDisposable

abstract open class ButtonsStripGroupListenerFragment : Fragment(), ButtonsStripGroup.Listener {
    protected val callback
        get() = (requireActivity() as MainView).selectionCallback
    private val container = CompositeDisposable()
    protected lateinit var model: TagSelectorModel
    protected lateinit var component: SelectorComponent
    lateinit var strip: ButtonsStripGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayout(), container, false)
        strip = view.findViewById(R.id.strip)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (requireActivity().applicationContext as App).appComponent
        component = appComponent.with(SelectorModule(requireActivity()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = getTitle()
        strip.listener = this
    }

    override fun onResume() {
        super.onResume()
        container.add(model.getTags().subscribe({ setItems(it) }, {}))
    }

    override fun onPause() {
        super.onPause()
        container.clear()
    }

    fun setItems(group: List<Pair<Tag, Boolean>>) {
        strip.setItems(group.map { it.first })
        strip.setItemsSelected(group)
    }

    abstract fun getItems(): List<ButtonsStrip.Item>

    override fun onItemSelected(item: ButtonsStrip.Item) {}

    override fun onItemDeselected(item: ButtonsStrip.Item) {}

    abstract fun getLayout(): Int

    abstract fun getTitle(): String?
}