package com.pppp.travelchecklist.list.view

import android.os.Bundle
import android.util.Log
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
import com.pppp.travelchecklist.edititem.view.EditItemDialogFragment
import com.pppp.travelchecklist.card.ChackListCardCallback
import com.pppp.travelchecklist.list.di.CheckListFragmentModule
import com.pppp.travelchecklist.list.viewmodel.SingleCheckListViewModel
import com.pppp.travelchecklist.main.MainActivity
import com.pppp.travelchecklist.utils.setTitleAndSubtitle
import kotlinx.android.synthetic.main.fragment_checlist.recycler
import javax.inject.Inject

class CheckListFragment : Fragment(), ChackListCardCallback {
    @Inject
    internal lateinit var viewModel: SingleCheckListViewModel

    private val listId
        get() = requireNotNull(arguments?.getString(LIST_ID))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_checlist, container, false);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.with(CheckListFragmentModule(this))?.inject(this@CheckListFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler.checkListCardCallback = this
        viewModel.viewStates(listId).observe(viewLifecycleOwner, Observer { render(it) })
        viewModel.events.observe(viewLifecycleOwner, Observer {
            when (it) {
                is SingleCheckListViewModel.TransientEvent.ListNotFound -> (activity as? MainActivity)?.onListNotAvailable()
            }
        })
    }

    private fun render(state: SingleCheckListViewModel.ViewState) {
        state.travelCheckList ?: return
        recycler.setItems(state.travelCheckList.categories, state.showChecked)
        (activity as? MainActivity)?.setTitleAndSubtitle(state.title, state.subTitle)
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

    override fun onSettingsClicked(cardId: String, itemId: String) {
        EditItemDialogFragment.newInstance(listId, cardId, itemId).show(getParentFragmentManager(), EditItemDialogFragment.TAG)
    }

    companion object {
        val TAG: String? = CheckListFragment::class.simpleName
        val LIST_ID = "list_id"

        fun fromSelection(listId: String) =
            CheckListFragment().apply {
                arguments = Bundle().apply {
                    putString(LIST_ID, listId)
                }
            }
    }
}