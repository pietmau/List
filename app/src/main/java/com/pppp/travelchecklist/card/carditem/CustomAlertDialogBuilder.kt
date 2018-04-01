package com.pppp.travelchecklist.card.carditem

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.SeekBar
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.model.CheckListItemData
import com.pppp.travelchecklist.model.Priority

class CustomAlertDialogBuilder(
        context: Context,
        private val data: CheckListItemData,
        private val cardPosition: Int,
        private val itemPosition: Int,
        private val callback: Callback
) : AlertDialog.Builder(context) {

    private var itemTitle: EditText?
    private var itemDescription: EditText?
    private var seekbar: SeekBar?

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_layout, null)
        setView(view)
        setTitle(context.getString(R.string.editItem))
        val cancel: DialogInterface.OnClickListener = DialogInterface.OnClickListener { dialog, id -> }
        setPositiveButton(context.getString(R.string.update)) { dialog, id -> onOkClicked() }
        setNegativeButton(context.getString(R.string.cancel), cancel)
        itemTitle = view.findViewById<EditText>(R.id.item_title)
        itemTitle?.setText(data.title)
        itemDescription = view.findViewById<EditText>(R.id.itemDescription)
        itemDescription?.setText(data.description)
        seekbar = view.findViewById<SeekBar>(R.id.seekbar)
        seekbar?.progress = data.priority.value
    }

    private fun onOkClicked() {
        val title = itemTitle?.text?.toString() ?: ""
        val description = itemDescription?.text?.toString()
        val progress = seekbar?.progress ?: 0
        val newData = CheckListItemData(title, data.checked, Priority(progress), description)
        callback.onItemEdited(newData, cardPosition, itemPosition)
    }

    interface Callback {
        fun onItemEdited(item: CheckListItemData, cardPosition: Int, itemPosition: Int)
    }
}