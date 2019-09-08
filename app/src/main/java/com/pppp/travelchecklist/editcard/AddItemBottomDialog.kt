package com.pppp.travelchecklist.editcard

import android.os.Bundle
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.bottomdialog.AddBottomDialog
import com.pppp.travelchecklist.list.bottomdialog.CategoryAdder
import com.pppp.travelchecklist.utils.appComponent
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.utils.getLong
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.input
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.title
import javax.inject.Inject

class AddItemBottomDialog : AddBottomDialog() {
    @Inject
    internal lateinit var categoryAdder: CategoryAdder

    override fun performInjection() {
        appComponent?.with(ViewCheckListModule(requireActivity()))?.inject(this)
    }

    override fun setUpViews() {
        title.setText(R.string.add_item)
        input.setHint(R.string.item_name)
    }

    override fun add(listId: String, name: String) {
        categoryAdder.addItem(listId, getLong(CATEGORY_ID)!!, name)
    }

    companion object {
        fun newInstance(listId: String, categoryId: Long) =
            AddItemBottomDialog().apply {
                arguments = Bundle().apply {
                    putString(LIST_ID, listId)
                    putLong(CATEGORY_ID, categoryId)
                }
            }

        val TAG = AddItemBottomDialog::class.java.simpleName
        val CATEGORY_ID = "category"

    }
}