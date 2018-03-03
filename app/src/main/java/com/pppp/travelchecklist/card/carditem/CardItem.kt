package com.pppp.travelchecklist.card.carditem

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.model.CheckListItemData
import kotlinx.android.synthetic.main.custom_check_list_card_item.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class CardItem(
        context: Context,
        private var data: CheckListItemData
) : RelativeLayout(context) {
    private val activity = context as Activity

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_check_list_card_item, this, true)
        check.text = data.title
        check.isChecked = data.checked
        settings.setOnClickListener { showSettings() }
        delete.setOnClickListener { showDelete() }
    }

    private fun showDelete() {
        activity.alert(context.getString(R.string.delete) + " " + data.title, context.getString(R.string.confirm_delete) + " " + data.title) {
            noButton { }
            yesButton { }
        }.show()
    }

    private fun showSettings() {
        CustomAlertDialogBuilder(context,data).create().show()
    }


}