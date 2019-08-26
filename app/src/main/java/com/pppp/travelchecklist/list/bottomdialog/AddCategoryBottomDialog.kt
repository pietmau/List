package com.pppp.travelchecklist.list.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.appComponent
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.done
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.name
import javax.inject.Inject

class AddCategoryBottomDialog : BottomSheetDialogFragment() {
    @Inject
    internal lateinit var categoryAdder: CategoryAdder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_dialog_addcategory, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.with(ViewCheckListModule(requireActivity()))?.inject(this@AddCategoryBottomDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        done.setOnClickListener {
            val text = name.text?.toString()
            val listId = arguments?.getString(LIST_ID)
            if (!text.isNullOrBlank() && !listId.isNullOrBlank()) {
                categoryAdder.addCategory(listId, text.capitalize())
            }
            dismiss()
        }
    }

    companion object {
        fun newInstance(listId: String) =
            AddCategoryBottomDialog().apply {
                arguments = Bundle().apply {
                    putString(LIST_ID, listId)
                }
            }

        val TAG = AddCategoryBottomDialog::class.java.simpleName
        val LIST_ID = "list_id"
    }
}