package com.pppp.travelchecklist.card.carditem

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.EditText
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.model.CheckListItemData

class CustomAlertDialogBuilder(
        context: Context,
        data: CheckListItemData) : AlertDialog.Builder(context) {

    private var itemTitle: EditText?

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_layout, null)
        setView(view)
        setTitle(context.getString(R.string.editItem))
        val ok: DialogInterface.OnClickListener = DialogInterface.OnClickListener { dialo, id -> }
        val cancel: DialogInterface.OnClickListener = DialogInterface.OnClickListener { dialog, id -> }
        setPositiveButton(context.getString(R.string.update), ok)
        setNegativeButton(context.getString(R.string.cancel), cancel)
        itemTitle = view.findViewById<EditText>(R.id.item_title)
        itemTitle?.setText(data.title.toCharArray(),0,data.title.length)
    }
}