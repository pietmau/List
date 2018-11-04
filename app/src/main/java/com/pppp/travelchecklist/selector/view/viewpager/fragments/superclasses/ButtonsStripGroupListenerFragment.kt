package com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.presenter.MainView
import com.pppp.travelchecklist.selector.SelectorComponent
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.selector.view.custom.ButtonsStripGroup
import com.pppp.travelchecklist.selector.view.viewpager.fragments.models.TagSelectorModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.button_strip_fragment.*

abstract open class ButtonsStripGroupListenerFragment : Fragment(), ButtonsStripGroup.Listener {
    protected val callback
        get() = (requireActivity() as MainView).selectionCallback
    private val subscriptions = CompositeDisposable()
    protected lateinit var model: TagSelectorModel
    protected lateinit var component: SelectorComponent
    lateinit var strip: ButtonsStripGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.button_strip_fragment, container, false)
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
        showProgress(true)
        subscriptions.add(model.getTags().subscribe({ setItems(it) }, { onError(it) }))
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }

    fun setItems(group: List<Pair<TagImpl, Boolean>>) {
        showProgress(false)
        strip.setItems(group.map { it.first })
        strip.setItemsSelected(group)
    }

    override fun onItemSelected(item: TagImpl) {
        model.onTagSelected(item)
    }

    override fun onItemDeselected(item: TagImpl) {
        model.onTagDeSeleected(item)
    }

    fun showProgress(show: Boolean) {
        progress.visibility = if (show) VISIBLE else GONE
        strip.visibility= if (!show) View.VISIBLE else View.GONE
    }

    private fun onError(throwable: Throwable?) {
        showProgress(false)
        val message = throwable?.localizedMessage ?: getString(R.string.something_went_wrong)
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).show()
    }

    abstract fun getTitle(): String?
}