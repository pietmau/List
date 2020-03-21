package com.pppp.travelchecklist.list.bottomdialog

import android.os.Bundle
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.utils.appComponent
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.input
import kotlinx.android.synthetic.main.fragment_dialog_addcategory.title
import javax.inject.Inject

class AddCategoryBottomDialog : AddBottomDialog() {

    @Inject
    internal lateinit var categoryAdder: CategoryAdder

    override fun performInjection() {
        appComponent?.viewCheckListComponentFactory?.create()?.inject(this)
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