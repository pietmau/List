package com.pppp.travelchecklist.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.appComponent
import com.pppp.travelchecklist.card.CheckListCard
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.list.viewmodel.CheckListViewModel
import com.pppp.travelchecklist.login.Consumer
import com.pppp.travelchecklist.login.Producer
import kotlinx.android.synthetic.main.fragment_blank.recycler
import javax.inject.Inject

class ViewCheckListFragment : Fragment(), CheckListCard.Callback {
    @Inject
    internal lateinit var producer: Producer<CheckListViewModel.ViewState>
    @Inject
    internal lateinit var consumer: Consumer<CheckListViewModel.ViewEvent>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_checlist, container, false);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(LIST_ID)?.let { listId ->
            appComponent?.with(ViewCheckListModule(listId, requireActivity()))?.inject(this@ViewCheckListFragment)
        }
        producer.states.observe(requireActivity(), Observer { render(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler.callback = this
    }

    private fun render(state: CheckListViewModel.ViewState) =
        when (state) {
            is CheckListViewModel.ViewState.Data -> onDataAvailable(state)
        }

    private fun onDataAvailable(state: CheckListViewModel.ViewState.Data) {
        recycler.setItems(state.travelCheckList.items)
    }

    override fun onItemDeleteRequested(cardPosition: Int, itemPosition: Int, data: CheckListItem) {
        consumer.push(CheckListViewModel.ViewEvent.Delete())
    }

    override fun onItemSettingsRequested(cardPosition: Int, itemPosition: Int, data: CheckListItem) {

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