package com.pppp.travelchecklist.list.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.cancel
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.done
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.input

abstract class AddBottomDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_dialog_addcategory, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performInjection()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpViews()
        done.setOnClickListener {
            val text = input.text?.toString()
            val listId = arguments?.getString(LIST_ID)
            if (!text.isNullOrBlank() && !listId.isNullOrBlank()) {
                add(listId, text)
            }
            dismiss()
        }
        cancel.setOnClickListener {
            dismiss()
        }
    }

    abstract fun performInjection()

    abstract fun add(listId: String, text: String)

    abstract fun setUpViews()

    companion object {
        val LIST_ID = "list_id"
    }
}