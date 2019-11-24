package com.pppp.travelchecklist.list.view.card.item

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.custom_item_attrs_view.view.description
import kotlinx.android.synthetic.main.custom_item_attrs_view.view.priority_flag

class ItemAttributesView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_item_attrs_view, this, true)
        orientation = HORIZONTAL
        layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
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
        when (priority) {
            1 -> setFlagTint(getColor(android.R.color.holo_green_dark))
            2 -> setFlagTint(getColor(android.R.color.holo_orange_dark))
            3 -> setFlagTint(getColor(android.R.color.holo_red_dark))
            else -> priority_flag.visibility = GONE
        }
    }

    private fun getColor(holoBlueDark: Int) = ContextCompat.getColor(context, holoBlueDark)

    private fun setFlagTint(color: Int) {
        ImageViewCompat.setImageTintList(priority_flag, ColorStateList.valueOf(color))
    }

}