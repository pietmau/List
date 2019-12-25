package com.pppp.travelchecklist.edititem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.utils.appComponent
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.description
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.save
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.slider_with_flag
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.title
import kotlinx.android.synthetic.main.view_slider_with_flag.slider
import javax.inject.Inject

class EditItemDialogFragment : BottomSheetDialogFragment() {
    @Inject
    lateinit var presenter: EditItemDialogFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.with(ViewCheckListModule(requireActivity()))?.inject(this@EditItemDialogFragment)
        presenter.getItem(
            requireNotNull(arguments?.getString(LIST_ID)),
            requireNotNull(arguments?.getString(CARD_ID)),
            requireNotNull(arguments?.getString(ITEM_ID)), { dismiss() }) {
            populateView(it)
        }
    }

    private fun populateView(checkListItem: CheckListItem) {
        title.setText(checkListItem.title, TextView.BufferType.EDITABLE)
        description.setText(checkListItem.description, TextView.BufferType.EDITABLE)
        save.setOnClickListener {
            presenter.onSaveClicked(title.textAsAString, description.textAsAString, slider_with_flag.value.toInt())
            dismiss()
        }
        slider_with_flag.value = checkListItem.priority.toFloat()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_edit_item, container, false)
    }

    companion object {
        fun newInstance(listId: String, cardId: String, itemId: String) =
            EditItemDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(LIST_ID, listId)
                    putString(CARD_ID, cardId)
                    putString(ITEM_ID, itemId)
                }
            }

        private const val CARD_ID = "card_id"
        private const val ITEM_ID = "item_id"
        private const val LIST_ID = "list_id"
        val TAG = EditItemDialogFragment::class.simpleName!!
    }
}

val EditText.textAsAString
    get() = text?.toString() ?: ""