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
import com.pppp.travelchecklist.list.view.recycler.card.CheckListCard
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.list.viewmodel.SingleCheckListViewModel
import com.pppp.travelchecklist.login.Consumer
import com.pppp.travelchecklist.login.Producer
import kotlinx.android.synthetic.main.fragment_blank.recycler
import javax.inject.Inject

class ViewCheckListFragment : Fragment(), CheckListCard.Callback {
    @Inject
    internal lateinit var producer: Producer<SingleCheckListViewModel.ViewState>
    @Inject
    internal lateinit var consumer: Consumer<SingleCheckListViewModel.ViewEvent>

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

    private fun render(state: SingleCheckListViewModel.ViewState) =
        when (state) {
            is SingleCheckListViewModel.ViewState.Data -> onDataAvailable(state)
        }

    private fun onDataAvailable(state: SingleCheckListViewModel.ViewState.Data) {
            recycler.setItems(state.travelCheckList.categories)
    }

    override fun onItemDeleteRequested(cardId: Long, itemId: Long, data: CheckListItem) {
        consumer.push(SingleCheckListViewModel.ViewEvent.DeleteItem(cardId, itemId))
    }

    override fun onItemSettingsRequested(cardId: Long, itemId: Long, data: CheckListItem) {

    }

    companion object {
        val LIST_ID = "list_id"

        fun fromSelection(listId: String) =
            ViewCheckListFragment().apply {
                arguments = Bundle().apply {
                    putString(LIST_ID, listId)
                }
            }
    }
}