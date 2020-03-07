package com.pppp.travelchecklist.confirmation

import android.os.Bundle
import android.view.View
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.main.MainActivity
import kotlinx.android.synthetic.main.fragment_dialog_confirmation.cancel
import kotlinx.android.synthetic.main.fragment_dialog_confirmation.ok
import kotlinx.android.synthetic.main.fragment_dialog_confirmation.title

class DeleteBottomConfirmationFragment : AddNewListBottomConfirmationFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        title.setText(R.string.delete_list)
        ok.setOnClickListener {
            (activity as MainActivity).onDialogConfirmed(DELETE_LIST)
            dismiss()
        }
        cancel.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val DELETE_LIST = "delete_list"
    }
}