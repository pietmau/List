package com.pppp.travelchecklist.edititem.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.edititem.di.EditItemModule
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewState
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.utils.appComponent
import com.pppp.travelchecklist.utils.requireStringArgument
import com.pppp.travelchecklist.utils.textAsAString
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.description
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.save
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.schedule
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.slider_with_flag
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.title
import javax.inject.Inject
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog

class EditItemDialogFragment : BottomSheetDialogFragment(), Callback, DatePickerDialog.OnDateSetListener {
    @Inject
    lateinit var viewStatesProducer: ViewStatesProducer<EditItemViewState>

    @Inject
    lateinit var viewActionsConsumer: ViewActionsConsumer<EditItemViewIntent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val module = EditItemModule(this, requireStringArgument(LIST_ID), requireStringArgument(CARD_ID), requireStringArgument(ITEM_ID))
        appComponent?.with(module)?.inject(this)
    }

    private fun populateView(checkListItem: CheckListItem, alert: Alert) {
        title.setText(checkListItem.title, TextView.BufferType.EDITABLE)
        description.setText(checkListItem.description, TextView.BufferType.EDITABLE)
//        save.setOnClickListener {
//            viewStatesProducer.onSaveClicked(
//                title.textAsAString,
//                description.textAsAString,
//                slider_with_flag.priority.toInt(),
//                requireStringArgument(LIST_ID),
//                requireStringArgument(CARD_ID),
//                requireStringArgument(ITEM_ID)
//            )
//            dismiss()
//        }
        schedule.callback = this
        schedule.formatter = requireNotNull(appComponent?.with(ViewCheckListModule(requireActivity()))?.formatter)
        schedule.alert = alert
        slider_with_flag.priority = checkListItem.priority.toFloat()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewStatesProducer.getItem(
//            requireStringArgument(LIST_ID),
//            requireStringArgument(CARD_ID),
//            requireStringArgument(ITEM_ID),
//            { dismiss() }) { item, alert -> populateView(item, alert) }// TODO use MVVM

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_edit_item, container, false)
    }

    override fun onAlertActivated(activated: Boolean) {
        //viewStatesProducer.onAlertActivated(activated)
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        //viewStatesProducer.onDateSet(year, monthOfYear, dayOfMonth)
    }

    override fun onDateClicked(alert: Alert) {
        TravelDatePickerDialog.newInstance(this, this)
            .show(requireNotNull(fragmentManager), TravelDatePickerDialog.TAG);
    }

    override fun onTimeClicked(alert: Alert) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance(listId: String, cardId: String, itemId: String) =
            EditItemDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(LIST_ID, listId)
                    putString(CARD_ID, cardId)
                    putString(ITEM_ID, itemId)
                }
            }

        private const val CARD_ID = "card_id"
        private const val ITEM_ID = "item_id"
        private const val LIST_ID = "list_id"
        val TAG = EditItemDialogFragment::class.simpleName!!
    }
}
