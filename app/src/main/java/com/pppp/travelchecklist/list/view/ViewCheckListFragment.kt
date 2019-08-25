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
import com.pppp.travelchecklist.list.view.card.CheckListCard
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.list.viewmodel.SingleCheckListViewModel
import com.pppp.travelchecklist.main.MainActivity
import kotlinx.android.synthetic.main.fragment_blank.recycler
import javax.inject.Inject

class ViewCheckListFragment : Fragment(), CheckListCard.Callback {
    @Inject
    internal lateinit var viewModel: SingleCheckListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_checlist, container, false);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.with(ViewCheckListModule(requireActivity()))?.inject(this@ViewCheckListFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler.callback = this
        viewModel.states(arguments?.getString(LIST_ID)!!).observe(viewLifecycleOwner, Observer { render(it) })
    }

    private fun render(state: SingleCheckListViewModel.ViewState) =
        when (state) {
            is SingleCheckListViewModel.ViewState.Data -> onDataAvailable(state)
        }

    private fun onDataAvailable(state: SingleCheckListViewModel.ViewState.Data) {
        recycler.setItems(state.travelCheckList.categories)
        (activity as? MainActivity)?.setListTitle(viewModel.getTitle(state.travelCheckList), viewModel.getSubTitle(state.travelCheckList))
    }

    override fun onItemDeleteRequested(cardId: Long, itemId: Long, data: CheckListItem) {
        sendToViewModel(SingleCheckListViewModel.ViewEvent.DeleteItem(cardId, itemId))
    }

    private fun sendToViewModel(viewEvent: SingleCheckListViewModel.ViewEvent) {
        viewModel.accept(viewEvent)
    }

    override fun onItemChecked(cardId: Long, itemId: Long, checked: Boolean) {
        sendToViewModel(SingleCheckListViewModel.ViewEvent.ItemChecked(cardId, itemId, checked))
    }

    override fun onItemMoved(cardId: Long, fromPosition: Int, toPosition: Int) {
        sendToViewModel(SingleCheckListViewModel.ViewEvent.MoveItem(cardId, fromPosition, toPosition))
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