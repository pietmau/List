package com.pppp.travelchecklist.edititem

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.utils.appComponent
import javax.inject.Inject

class EditItemDialogFragment : DialogFragment() {
    @Inject
    lateinit var presenter: EditItemDialogFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.with(ViewCheckListModule(requireActivity()))?.inject(this@EditItemDialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.apply {
            setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->
                })
            setNegativeButton(R.string.cancel,
                DialogInterface.OnClickListener { dialog, id ->
                })
        }
        return builder.create()
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