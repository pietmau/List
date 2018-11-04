package com.pppp.travelchecklist.selector.view.viewpager.fragments.superclasses

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.presenter.MainView
import com.pppp.travelchecklist.selector.SelectorComponent
import com.pppp.travelchecklist.selector.SelectorModule
import com.pppp.travelchecklist.selector.view.custom.ButtonsStrip
import com.pppp.travelchecklist.selector.view.viewpager.fragments.models.TagSelectorModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.item_selector_fragment.*

abstract class ItemSelectorFragment : Fragment(), ButtonsStrip.Callback {
    protected val disposables = CompositeDisposable()
    abstract protected var model: TagSelectorModel
    protected val callback
        get() = (requireActivity() as MainView).selectionCallback
    protected lateinit var component: SelectorComponent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = getTitle()
        strip.callback = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (requireActivity().applicationContext as App).appComponent
        component = appComponent.with(SelectorModule(requireActivity()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.item_selector_fragment, container, false)

    override fun onResume() {
        super.onResume()
        showProgress(true)
        disposables.add(model.getTags().subscribe({ setItems(it) }, { onError(it) }))
    }

    private fun onError(throwable: Throwable?) {
        showProgress(false)
        val message = throwable?.localizedMessage ?: getString(R.string.something_went_wrong)
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        disposables.clear()
    }

    abstract fun setItems(group: List<Pair<TagImpl, Boolean>>)

    override fun onItemSelected(item: TagImpl) {
        model.onTagSelected(item)
    }

    override fun onItemDeSelected(item: TagImpl) {
        model.onTagDeSeleected(item)
    }

    fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
        strip.visibility= if (!show) View.VISIBLE else View.GONE
    }

    abstract fun getTitle(): String?
}