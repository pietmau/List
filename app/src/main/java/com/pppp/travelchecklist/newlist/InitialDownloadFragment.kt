package com.pppp.travelchecklist.newlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.main.presenter.CreateChecklistView
import com.pppp.travelchecklist.newlist.di.NewListModule
import com.pppp.travelchecklist.newlist.model.TagsCache
import com.pppp.travelchecklist.newlist.view.NewListFragment
import javax.inject.Inject

class InitialDownloadFragment : Fragment() {
    @Inject
    lateinit var tagsCache: TagsCache

    private val parent
        get() = (activity as? CreateChecklistView)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_initialdownload, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (requireActivity().applicationContext as App).appComponent
        appComponent.with(NewListModule(requireActivity())).inject(this)
        tagsCache.getTags.observe(requireActivity(), Observer { event ->
            when (event) {
                is TagsCache.Event.Failure -> onError(event)
                is TagsCache.Event.Success -> parent?.navigateToSelector()
            }
        })
    }

    private fun onError(event: TagsCache.Event.Failure) {
        parent?.onError(event.exception.localizedMessage)
    }

    companion object {
        fun newInstance() = NewListFragment()
    }
}