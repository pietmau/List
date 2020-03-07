package com.pppp.travelchecklist.item

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.pppp.travelchecklist.R

class FlagImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatImageView(context, attrs, defStyleAttr) {

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

    private fun getColor(@ColorRes color: Int) = ContextCompat.getColor(context, color)

    private fun setFlagTint(color: Int) {
        setColorFilter(color)
    }
}