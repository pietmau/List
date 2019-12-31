package com.pppp.travelchecklist.list.view.card.item

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.custom_item_attrs_view.view.priority_flag
import java.lang.UnsupportedOperationException

class FlagImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    ImageView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        setImageResource(R.drawable.ic_flag_svgrepo_com)
    }

    fun setFlag(priority: Int) {
        when (priority) {
            0 -> Unit
            1 -> setFlagTint(getColor(android.R.color.holo_green_light))
            2 -> setFlagTint(getColor(android.R.color.holo_orange_light))
            3 -> setFlagTint(getColor(android.R.color.holo_red_light))
            else -> throw UnsupportedOperationException("Invalid priority for priority flag: " + priority)
        }
    }

    private fun getColor(holoBlueDark: Int) = ContextCompat.getColor(context, holoBlueDark)

    private fun setFlagTint(color: Int) {
        ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(color))
    }
}