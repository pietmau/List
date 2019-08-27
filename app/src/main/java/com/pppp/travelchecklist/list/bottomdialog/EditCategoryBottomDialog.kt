package com.pppp.travelchecklist.list.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.appComponent
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import javax.inject.Inject

class EditCategoryBottomDialog : BottomSheetDialogFragment() {
    @Inject
    internal lateinit var categoryAdder: CategoryAdder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_dialog_editcategory, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.with(ViewCheckListModule(requireActivity()))?.inject(this@EditCategoryBottomDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        fun newInstance(listId: String, categoryId: Long) =
            EditCategoryBottomDialog().apply {
                arguments = Bundle().apply {
                    putString(LIST_ID, listId)
                    putLong(CATEGORY_ID, categoryId)
                }
            }

        val LIST_ID = "list_id"
        val TAG = EditCategoryBottomDialog::class.simpleName!!
        val CATEGORY_ID = "category_id"
    }
}