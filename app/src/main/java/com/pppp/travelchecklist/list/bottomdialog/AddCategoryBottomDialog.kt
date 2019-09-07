package com.pppp.travelchecklist.list.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.utils.appComponent
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.done
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.input
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.title
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.view.done
import javax.inject.Inject

class AddCategoryBottomDialog : AddBottomDialog() {

    @Inject
    internal lateinit var categoryAdder: CategoryAdder

    override fun performInjection() {
        appComponent?.with(ViewCheckListModule(requireActivity()))?.inject(this)
    }

    override fun setUpViews() {
        title.setText(R.string.add_new_card)
        input.setHint(R.string.card_title)
    }

    override fun add(listId: String, text: String) {
        categoryAdder.addCategory(listId, text.capitalize())
    }

    companion object {
        fun newInstance(listId: String) =
            AddCategoryBottomDialog().apply {
                arguments = Bundle().apply {
                    putString(LIST_ID, listId)
                }
            }

        val TAG = AddCategoryBottomDialog::class.java.simpleName
    }
}