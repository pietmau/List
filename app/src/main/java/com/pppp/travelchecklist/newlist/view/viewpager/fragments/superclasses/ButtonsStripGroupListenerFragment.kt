package com.pppp.travelchecklist.newlist.view.viewpager.fragments.superclasses

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.pietrantuono.entities.Tag
import com.pppp.entities.pokos.TagImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.presenter.CreateChecklistView
import com.pppp.travelchecklist.newlist.NewListActivity
import com.pppp.travelchecklist.newlist.di.NewListComponent
import com.pppp.travelchecklist.newlist.di.NewListModule
import com.pppp.travelchecklist.newlist.model.models.TagSelectorModel
import com.pppp.travelchecklist.newlist.view.custom.ButtonsStripGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.button_strip_fragment.*

abstract class ButtonsStripGroupListenerFragment : Fragment(), ButtonsStripGroup.Listener {
    protected val callback
        get() = (requireActivity() as? CreateChecklistView)?.selectionCallback
    protected lateinit var model: TagSelectorModel
    protected lateinit var component: NewListComponent
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
        component = appComponent.with(NewListModule(requireActivity() as NewListActivity))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        strip.title = getTitle()
        strip.listener = this
        setItems(model.tags.toList())
    }

    fun setItems(group: List<Pair<Tag, Boolean>>) {
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
        progress_container.visibility = if (show) VISIBLE else GONE
        strip.visibility = if (!show) View.VISIBLE else View.GONE
    }

    abstract fun getTitle(): String?
}