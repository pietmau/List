package com.pppp.travelchecklist.edititem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.utils.appComponent
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.description
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.save
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.schedule
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.slider_with_flag
import kotlinx.android.synthetic.main.fragment_dialog_edit_item.title
import javax.inject.Inject
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog

class EditItemDialogFragment : BottomSheetDialogFragment(), Callback, DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var presenter: EditItemDialogFragmentPresenter

    private val listId
        get() = requireNotNull(arguments?.getString(LIST_ID))

    private val cardId
        get() = requireNotNull(arguments?.getString(CARD_ID))

    private val itemId
        get() = requireNotNull(arguments?.getString(ITEM_ID))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.with(ViewCheckListModule(requireActivity()))?.inject(this@EditItemDialogFragment)
        presenter.getItem(
            listId,
            cardId,
            itemId, { dismiss() }) {
            populateView(it)
        }
    }

    private fun populateView(checkListItem: CheckListItem) {
        title.setText(checkListItem.title, TextView.BufferType.EDITABLE)
        description.setText(checkListItem.description, TextView.BufferType.EDITABLE)
        save.setOnClickListener {
            presenter.onSaveClicked(title.textAsAString, description.textAsAString, slider_with_flag.value.toInt(), listId, cardId, itemId)
            dismiss()
        }
        slider_with_flag.value = checkListItem.priority.toFloat()
        schedule.callback = this
        schedule.alert = Alert((checkListItem as CheckListItemImpl).alertTimeInMills, checkListItem.isAlertOn)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_edit_item, container, false)
    }

    override fun onAlertActivated(alert: Alert, activated: Boolean) {

    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        presenter.onDateSet(year, monthOfYear, dayOfMonth)
    }

    override fun onDateClicked(alert: Alert) {
        TravelDatePickerDialog.newInstance(this, this).show(requireNotNull(fragmentManager), TravelDatePickerDialog.TAG);
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

val EditText.textAsAString
    get() = text?.toString() ?: ""