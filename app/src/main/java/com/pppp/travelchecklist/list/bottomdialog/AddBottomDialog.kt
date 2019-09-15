package com.pppp.travelchecklist.list.bottomdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.R
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
            val travelCheckListImpl = arguments?.getParcelable<TravelCheckListImpl>(LIST)
            if (!text.isNullOrBlank()) {
                add(travelCheckListImpl!!, text)
            }
            dismiss()
        }
    }

    abstract fun performInjection()

    abstract fun add(listId: TravelCheckListImpl, text: String)

    abstract fun setUpViews()

    companion object {
        val LIST = "list"
        val CATEGORY_ID = "category"
    }
}