package com.pppp.travelchecklist.edititem.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.edititem.di.EditItemModule
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.OnDateClicked
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.DateSet
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.OnAlertActivated
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewState
import com.pppp.travelchecklist.utils.appComponent
import com.pppp.travelchecklist.utils.requireStringArgument
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.description
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewStatesProducer.states.observe(requireActivity(), Observer { render(it) })
        schedule.callback = this
    }

    private fun render(editItemViewState: EditItemViewState) {
        schedule.checked = editItemViewState.isAlertOn
        schedule.date = editItemViewState.date
        schedule.time = editItemViewState.time
        slider_with_flag.priority = editItemViewState.priority
        title.setText(editItemViewState.title, TextView.BufferType.EDITABLE)
        description.setText(editItemViewState.description, TextView.BufferType.EDITABLE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_edit_item, container, false)
    }

    override fun onAlertActivated(activated: Boolean) {
        emit(OnAlertActivated(activated))
    }

    override fun onDateClicked() {
        emit(OnDateClicked)
        //TravelDatePickerDialog.newInstance(this, this).show(requireNotNull(fragmentManager), TravelDatePickerDialog.TAG);
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        emit(DateSet(year, monthOfYear, dayOfMonth))
    }

    private fun emit(intent: EditItemViewIntent) {
        viewActionsConsumer.accept(intent)
    }


    override fun onTimeClicked() {
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
