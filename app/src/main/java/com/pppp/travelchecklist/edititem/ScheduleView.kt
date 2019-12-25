package com.pppp.travelchecklist.edititem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.view_slider_with_flag.view.slider

class ScheduleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_slider_with_flag, this, true);
    }
}