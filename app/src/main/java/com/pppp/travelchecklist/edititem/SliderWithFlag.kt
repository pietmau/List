package com.pppp.travelchecklist.edititem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.view_slider_with_flag.view.label
import kotlinx.android.synthetic.main.view_slider_with_flag.view.slider
import java.lang.UnsupportedOperationException

class SliderWithFlag @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    var priority: Float
        get() = slider.value
        set(input) {
            slider.value = input
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_slider_with_flag, this, true);
        slider.setOnChangeListener { _, value ->
            onValueChange(value.toInt())
        }
        setLabelText(R.string.priority_none)
    }

    private fun setLabelText(priorityNone: Int) {
        label.setText(priorityNone)
    }

    private fun onValueChange(value: Int) {
        setText(value)
        setFlag(value)
    }

    private fun setText(value: Int) {
        when (value) {
            0 -> setLabelText(R.string.priority_none)
            1 -> setLabelText(R.string.priority_low)
            2 -> setLabelText(R.string.priority_medium)
            3 -> setLabelText(R.string.priority_high)
            else -> throw UnsupportedOperationException("Invalid priority for priority flag: " + value)
        }
    }

    fun setFlag(priority: Int) {
        when (priority) {
            0 -> setTintedDrawable(getColor(android.R.color.transparent))
            1 -> setTintedDrawable(getColor(android.R.color.holo_green_light))
            2 -> setTintedDrawable(getColor(android.R.color.holo_orange_light))
            3 -> setTintedDrawable(getColor(android.R.color.holo_red_light))
            else -> throw UnsupportedOperationException("Invalid priority for priority flag: " + priority)
        }
    }

    private fun getColor(holoBlueDark: Int) = ContextCompat.getColor(context, holoBlueDark)

    private fun setTintedDrawable(color: Int) {
        val drawable = label.compoundDrawablesRelative.filterNotNull().first().apply { setTint(color) }
        label.setCompoundDrawablesRelative(null, null, drawable, null)
    }
}