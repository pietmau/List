package com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.entities.Tag
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.presenter.MainView
import com.pppp.travelchecklist.selector.SelectorComponent
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.viewpager.fragments.TagSelectorModel
import io.reactivex.disposables.CompositeDisposable

abstract class ItemSelectorFragment : Fragment(), ButtonsStrip.Callback {
    protected val container = CompositeDisposable()
    abstract protected var model: TagSelectorModel
    protected val callback
        get() = (requireActivity() as MainView).selectionCallback
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

    override fun onResume() {
        super.onResume()
        container.add(model.getTags().subscribe({ setItems(it) }, {}))
    }

    override fun onPause() {
        super.onPause()
        container.clear()
    }

    abstract fun setItems(group: List<Pair<Tag, Boolean>>)

    override abstract fun onItemSelected(item: Tag?)

    override fun onItemDeSelected(item: Tag?) {/*NoOp*/
    }


}