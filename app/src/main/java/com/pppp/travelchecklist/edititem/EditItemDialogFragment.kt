package com.pppp.travelchecklist.edititem

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.utils.appComponent
import javax.inject.Inject

class EditItemDialogFragment : DialogFragment() {
    private lateinit var alertDialog: AlertDialog
    @Inject
    lateinit var presenter: EditItemDialogFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.with(ViewCheckListModule(requireActivity()))?.inject(this@EditItemDialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        with(builder) {
            setPositiveButton(R.string.ok,
                { _, id ->
                })
            setNegativeButton(R.string.cancel,
                { _, id ->
                })
            setTitle(R.string.editItem)
            setView(getLayout())
        }
        alertDialog = builder.create()
        return alertDialog
    }

    private fun getLayout() = R.layout.custom_dialog_layout

    private fun newView(): View {
        return TextView(requireContext()).apply { text = "dsdasd" }
    }

    companion object {
        fun newInstance(cardId: String, itemId: String) = EditItemDialogFragment().apply {
            arguments?.putString(CARD_ID, cardId)
            arguments?.putString(ITEM_ID, itemId)
        }

        private const val CARD_ID = "card_id"
        private const val ITEM_ID = "item_id"
        val TAG = EditItemDialogFragment::class.simpleName!!
    }
}