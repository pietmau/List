package com.pppp.travelchecklist.editcard

import android.os.Bundle
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.bottomdialog.AddBottomDialog
import com.pppp.travelchecklist.list.bottomdialog.CategoryAdder
import com.pppp.travelchecklist.utils.appComponent
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.utils.getLongArgument
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

    override fun add(travelCheckList: TravelCheckListImpl, name: String) {
        categoryAdder.addItem(travelCheckList.id!!, getLongArgument(CATEGORY_ID)!!, name)
    }

    companion object {
        fun newInstance(list: TravelCheckListImpl, categoryId: Long) =
            AddItemBottomDialog().apply {
                arguments = Bundle().apply {
                    putParcelable(LIST, list)
                    putLong(CATEGORY_ID, categoryId)
                }
            }
    }
}