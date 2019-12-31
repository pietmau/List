package com.pppp.travelchecklist.list.view.card.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.custom_item_attrs_view.view.description
import kotlinx.android.synthetic.main.custom_item_attrs_view.view.priority_flag

class ItemAttributesView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_item_attrs_view, this, true)
        orientation = HORIZONTAL
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    }

    fun setData(checkListItem: CheckListItem) {
        setUpPriority(checkListItem.priority)
        setUpDescription(checkListItem.description)
    }

    private fun setUpDescription(itemDescription: String?) {
        description.visibility = if (itemDescription.isNullOrBlank()) GONE else VISIBLE
    }

    private fun setUpPriority(priority: Int) {
        priority_flag.visibility = if (priority > 0) VISIBLE else GONE
        priority_flag.setFlag(priority)
    }
}