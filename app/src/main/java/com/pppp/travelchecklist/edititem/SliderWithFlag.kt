package com.pppp.travelchecklist.edititem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.view_slider_with_flag.view.flag
import kotlinx.android.synthetic.main.view_slider_with_flag.view.slider

class SliderWithFlag @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs, defStyleAttr) {

    var value: Float
        get() = slider.value
        set(input) {
            slider.value = input
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_slider_with_flag, this, true);
        slider.setOnChangeListener { _, value ->
            onValueChange(value.toInt())
        }
    }

    private fun onValueChange(value: Int) {
        flag.visibility = if (value > 0) VISIBLE else INVISIBLE
        flag.setFlag(value)
    }
}