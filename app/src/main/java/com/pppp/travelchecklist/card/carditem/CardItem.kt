package com.pppp.travelchecklist.card.carditem

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.custom_check_list_card_item.view.*

class CardItem(
    context: Context,
    val data: CheckListItem,
    val itemPosition: Int,
    val callback: Callback
) : RelativeLayout(context) {

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_check_list_card_item, this, true)
        check.text = data.title
        check.isChecked = data?.checked == true
        settings.setOnClickListener { showSettings() }
        delete.setOnClickListener { showDelete() }
    }

    private fun showDelete() {
        callback.onDeleteRequested(itemPosition, data)
    }

    private fun showSettings() {
        callback.onSettingsRequested(itemPosition, data)
    }

    interface Callback {
        fun onDeleteRequested(position: Int, data: CheckListItem)
        fun onSettingsRequested(position: Int, data: CheckListItem)
    }
}