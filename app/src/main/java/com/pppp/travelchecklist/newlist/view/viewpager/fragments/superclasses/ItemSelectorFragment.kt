package com.pppp.travelchecklist.newlist.view.viewpager.fragments.superclasses

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.presenter.CreateChecklistView
import com.pppp.travelchecklist.newlist.NewListActivity
import com.pppp.travelchecklist.newlist.di.NewListComponent
import com.pppp.travelchecklist.newlist.di.NewListModule
import com.pppp.travelchecklist.newlist.model.models.TagSelectorModel
import com.pppp.travelchecklist.newlist.view.custom.ButtonsStrip
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.item_selector_fragment.*

abstract class ItemSelectorFragment : Fragment(), ButtonsStrip.Callback {
    abstract protected var model: TagSelectorModel
    protected val callback
        get() = (requireActivity() as CreateChecklistView).selectionCallback
    protected lateinit var component: NewListComponent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = getTitle()
        strip.callback = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (requireActivity().applicationContext as App).appComponent
        component = appComponent.with(NewListModule(requireActivity() as NewListActivity))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.item_selector_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setItems(model.tags.toList())
    }

    abstract fun setItems(group: List<Pair<Tag, Boolean>>)

    override fun onItemSelected(item: Tag) {
        model.onTagSelected(item)
    }

    override fun onItemDeSelected(item: Tag) {
        model.onTagDeSeleected(item)
    }

    fun showProgress(show: Boolean) {
        progress_container.visibility = if (show) View.VISIBLE else View.GONE
        strip.visibility = if (!show) View.VISIBLE else View.GONE
    }

    abstract fun getTitle(): String?
}