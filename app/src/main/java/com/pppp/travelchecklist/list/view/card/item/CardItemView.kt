package com.pppp.travelchecklist.list.view.card.item

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.pietrantuono.entities.CheckListItem
import kotlinx.android.synthetic.main.custom_check_list_card_item.view.*

class CardItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    var data: CheckListItem? = null
        set(value) {
            field = value
            title.text = value?.title
            check.isChecked = value?.checked == true
            attributes.setData(requireNotNull(data))
        }

    var itemId: String? = null
    var callback: Callback? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(com.pppp.travelchecklist.R.layout.custom_check_list_card_item, this, true)
        check.setOnClickListener { buttonView ->
            val checked = requireNotNull(data).checked
            check.setPaintFlags(getPaint(!checked));
            callback?.onItemChecked(requireNotNull(data?.id), !checked)
        }
        setOnClickListener {
            callback?.onSettingsClicked(requireNotNull(data))
        }
    }

    private fun getPaint(checked: Boolean) = if (checked) Paint.STRIKE_THRU_TEXT_FLAG else Paint.LINEAR_TEXT_FLAG

    interface Callback {
        fun onDeleteRequested(id: String, data: CheckListItem)
        fun onItemChecked(id: String, checked: Boolean)
        fun onItemMoved(fromPosition: Int, toPosition: Int)
        fun onSettingsClicked(data: CheckListItem)
    }
}