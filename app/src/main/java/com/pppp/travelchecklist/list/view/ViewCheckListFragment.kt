package com.pppp.travelchecklist.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.utils.appComponent
import com.pppp.travelchecklist.list.bottomdialog.AddCategoryBottomDialog
import com.pppp.travelchecklist.editcard.EditCategoryBottomDialog
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.list.view.card.ChackListCardCallback
import com.pppp.travelchecklist.list.viewmodel.SingleCheckListViewModel
import com.pppp.travelchecklist.main.MainActivity
import kotlinx.android.synthetic.main.fragment_checlist.recycler
import javax.inject.Inject

class ViewCheckListFragment : Fragment(), ChackListCardCallback {
    @Inject
    internal lateinit var viewModel: SingleCheckListViewModel

    private val listId
        get() = requireNotNull(arguments?.getString(LIST_ID))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_checlist, container, false);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.with(ViewCheckListModule(requireActivity()))?.inject(this@ViewCheckListFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler.chackListCardCallback = this
        viewModel.states(listId).observe(viewLifecycleOwner, Observer { render(it) })
    }

    private fun render(state: SingleCheckListViewModel.ViewState) {
        state.travelCheckList ?: return
        recycler.setItems(state.travelCheckList.categories, state.showChecked)
        (activity as? MainActivity)?.setListTitle(state.title, state.title)
    }

    override fun onItemDeleteRequested(cardId: String, itemId: String, data: CheckListItem) {
        sendToViewModel(SingleCheckListViewModel.SingleListViewEvent.DeleteItem(listId, cardId, itemId))
    }

    private fun sendToViewModel(singleListViewEvent: SingleCheckListViewModel.SingleListViewEvent) {
        viewModel.accept(singleListViewEvent)
    }

    override fun onItemChecked(cardId: String, itemId: String, checked: Boolean) {
        sendToViewModel(SingleCheckListViewModel.SingleListViewEvent.ItemChecked(listId, cardId, itemId, checked))
    }

    override fun onItemMoved(cardId: String, fromPosition: Int, toPosition: Int) {
        sendToViewModel(SingleCheckListViewModel.SingleListViewEvent.MoveItem(listId, cardId, fromPosition, toPosition))
    }

    fun addCategory() {
        AddCategoryBottomDialog.newInstance(listId).show(requireFragmentManager(), AddCategoryBottomDialog.TAG)
    }

    override fun onCardOptionsClicked(cardId: String) {
        EditCategoryBottomDialog.newInstance(listId, cardId).show(requireFragmentManager(), AddCategoryBottomDialog.TAG)
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