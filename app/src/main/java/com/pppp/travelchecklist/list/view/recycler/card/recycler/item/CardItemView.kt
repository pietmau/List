package com.pppp.travelchecklist.list.view.recycler.card.recycler.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.custom_check_list_card_item.view.*

class CardItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    var data: CheckListItem? = null
        set(value) {
            check.text = value?.title
            check.isChecked = value?.checked == true
            field = value
        }

    var itemPosition: Int? = null
    var callback: Callback? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_check_list_card_item, this, true)
        settings.setOnClickListener { showSettings() }
        delete.setOnClickListener { showDelete() }
    }

    private fun showDelete() {
        callback?.onDeleteRequested(itemPosition!!, data!!)//let it crash!
    }

    private fun showSettings() {
        callback?.onSettingsRequested(itemPosition!!, data!!)
    }

    interface Callback {
        fun onDeleteRequested(position: Int, data: CheckListItem)
        fun onSettingsRequested(position: Int, data: CheckListItem)
    }
}