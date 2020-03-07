package com.pppp.travelchecklist.confirmation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.main.MainActivity
import kotlinx.android.synthetic.main.fragment_dialog_confirmation.cancel
import kotlinx.android.synthetic.main.fragment_dialog_confirmation.ok
import kotlinx.android.synthetic.main.fragment_dialog_confirmation.title

open class AddNewListBottomConfirmationFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_dialog_confirmation, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        title.setText(R.string.add_list)
        ok.setOnClickListener {
            (activity as MainActivity).onDialogConfirmed(ADD_LIST)
            dismiss()
        }
        cancel.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val ADD_LIST = "add_list"
    }
}