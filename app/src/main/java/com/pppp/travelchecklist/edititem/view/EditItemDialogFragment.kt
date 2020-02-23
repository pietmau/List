package com.pppp.travelchecklist.edititem.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.edititem.di.EditItemModule
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemTransientEvent
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemTransientEvent.SaveClicked
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemTransientEvent.SelectDate
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemTransientEvent.SelectTime
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.DateSet
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.OnAlertActivated
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.OnDataChanged
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.OnDateClicked
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.OnSaveClicked
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.OnTimeClicked
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.OnTimeSet
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewState
import com.pppp.travelchecklist.notifications.bootreceiver.BootReceiver
import com.pppp.travelchecklist.utils.appComponent
import com.pppp.travelchecklist.utils.requireStringArgument
import com.pppp.travelchecklist.utils.setAfterChangeListener
import com.pppp.travelchecklist.utils.textAsAString
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.description
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.save
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.schedule
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.slider_with_flag
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.title
import javax.inject.Inject

class EditItemDialogFragment : BottomSheetDialogFragment(), Callback, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @Inject
    lateinit var viewStatesProducer: ViewStatesProducer<EditItemViewState>
    @Inject
    lateinit var viewActionsConsumer: ViewActionsConsumer<EditItemViewIntent>
    @Inject
    lateinit var transientEventsProducer: TransientEventsProducer<EditItemTransientEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val module = EditItemModule(this, requireStringArgument(LIST_ID), requireStringArgument(CARD_ID), requireStringArgument(ITEM_ID))
        appComponent?.with(module)?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewStatesProducer.states.observe(requireActivity(), Observer { render(it) })
        transientEventsProducer.transientEvents.observe(requireActivity(), Observer { onTransientEventReceived(it) })
        schedule.callback = this
        title.setAfterChangeListener {
            emit(OnDataChanged(title = it))
        }
        description.setAfterChangeListener {
            emit(OnDataChanged(description = it))
        }
        save.setOnClickListener {
            emit(OnSaveClicked)
            dismiss()
        }
        slider_with_flag.callback = {
            emit(OnDataChanged(priority = it))
        }
    }

    private fun onTransientEventReceived(transientEvent: EditItemTransientEvent) =
        when (transientEvent) {
            is SelectDate -> showDatePicker(transientEvent.timeInMills)
            is SelectTime -> showTimePicker(transientEvent.timeInMills)
            is SaveClicked -> onSaveClicked()
        }

    private fun showTimePicker(timeInMills: Long) {
        TravelTimePickerDialog.newInstance(this, this, timeInMills).show(requireNotNull(fragmentManager), TravelDatePickerDialog.TAG);
    }

    private fun showDatePicker(timeInMills: Long) {
        TravelDatePickerDialog.newInstance(this, this, timeInMills).show(requireNotNull(fragmentManager), TravelDatePickerDialog.TAG);
    }

    private fun render(editItemViewState: EditItemViewState) {
        schedule.checked = editItemViewState.isAlertOn
        schedule.date = editItemViewState.date
        schedule.time = editItemViewState.time
        slider_with_flag.priority = editItemViewState.priority
        title.textAsAString = editItemViewState.title
        description.textAsAString = editItemViewState.description
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_dialog_edit_item, container, false)

    override fun onAlertActivated(activated: Boolean) = emit(OnAlertActivated(activated))

    override fun onDateClicked() = emit(OnDateClicked)

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) = emit(DateSet(year, monthOfYear, dayOfMonth))

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) = emit(OnTimeSet(hourOfDay, minute))

    private fun emit(intent: EditItemViewIntent) = viewActionsConsumer.accept(intent)

    override fun onTimeClicked() = emit(OnTimeClicked)

    private fun onSaveClicked() {
        requireContext().sendBroadcast(Intent(requireContext(), BootReceiver::class.java).apply {
            action = "com.pppp.travelchecklist.pppp.SAVE"
        })
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
