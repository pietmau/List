package com.pppp.travelchecklist.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.appComponent
import com.pppp.travelchecklist.application.App
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.list.viewmodel.CheckListViewModel
import com.pppp.travelchecklist.selector.presenter.SelectionData
import javax.inject.Inject

class ViewCheckListFragment : Fragment() {
    @Inject
    private lateinit var viewModel: CheckListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_checlist, container, false);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.get(LIST_ID)?.let { listId ->
            appComponent?.with(ViewCheckListModule("", requireActivity()))?.inject(this@ViewCheckListFragment)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {
        val TAG = ViewCheckListFragment::class.java.simpleName
        val LIST_ID = "list_id"

        fun fromSelection(listId: String) =
            ViewCheckListFragment().apply {
                arguments = Bundle().apply {
                    putString(LIST_ID, listId)
                }
            }
    }
}