package com.pppp.travelchecklist.list.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.done

class AddCategoryBottomDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_dialog_addcategory, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        done.setOnClickListener { }
    }

    companion object {
        val TAG = AddCategoryBottomDialog::class.java.simpleName
    }
}