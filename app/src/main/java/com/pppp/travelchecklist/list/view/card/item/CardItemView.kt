package com.pppp.travelchecklist.list.view.card.item

import android.content.Context
import android.graphics.Paint
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

    var itemId: Long? = null
    var callback: Callback? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_check_list_card_item, this, true)
        check.setOnCheckedChangeListener { buttonView, isChecked ->
            buttonView.setPaintFlags(getPaint(isChecked));
            callback?.onItemChecked(data?.id!!, isChecked)
        }
    }

    private fun getPaint(checked: Boolean) = if (checked) Paint.STRIKE_THRU_TEXT_FLAG else Paint.LINEAR_TEXT_FLAG

    interface Callback {
        fun onDeleteRequested(position: Long, data: CheckListItem)
        fun onSettingsRequested(position: Long, data: CheckListItem)
        fun onItemChecked(id: Long, checked: Boolean)
        fun onItemMoved(fromPosition: Int, toPosition: Int)
    }
}