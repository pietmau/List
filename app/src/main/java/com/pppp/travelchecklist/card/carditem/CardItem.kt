package com.pppp.travelchecklist.card.carditem

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.pppp.entities.pokos.CheckListItem
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.custom_check_list_card_item.view.*

class CardItem(
    context: Context,
    val data: CheckListItem,
    val position: Int,
    val callback: Callback
) : RelativeLayout(context) {
    private val activity = context as Activity

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_check_list_card_item, this, true)
        check.text = data.title
        check.isChecked = if (data?.checked == true) true else false
        settings.setOnClickListener { showSettings() }
        delete.setOnClickListener { showDelete() }
    }

    private fun showDelete() {
        callback.onDeleteRequested(position, data)
        /*activity.alert(context.getString(R.string.delete) + " " + data.title, context.getString(R.string.confirm_delete) + " " + data.title) {
            noButton { }
            yesButton { }
        }.show()*/
    }

    private fun showSettings() {
        callback.onSettingsRequested(position)
        //CustomAlertDialogBuilder(context, data).create().show()
    }

    interface Callback {
        fun onDeleteRequested(position: Int, data: CheckListItem)
        fun onSettingsRequested(position: Int)
    }
}