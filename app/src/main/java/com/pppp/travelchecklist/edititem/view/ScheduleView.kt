package com.pppp.travelchecklist.edititem.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.view_schedule_view.view.container
import kotlinx.android.synthetic.main.view_schedule_view.view.dateText
import kotlinx.android.synthetic.main.view_schedule_view.view.timeText
import kotlinx.android.synthetic.main.view_schedule_view.view.toggle

class ScheduleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {
    internal var callback: Callback? = null
    internal var checked: Boolean = false
        set(value) {
            container.visibility = if (value) VISIBLE else INVISIBLE
        }
    internal var date: String? = null
        set(value) {
            dateText.text = value
        }
    internal var time: String? = null
        set(value) {
            timeText.text = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_schedule_view, this, true);
        toggle.setOnCheckedChangeListener { _, isChecked ->
            callback?.onAlertActivated(isChecked)
            checked = isChecked
        }
        dateText.setOnClickListener { callback?.onDateClicked() }
        timeText.setOnClickListener { callback?.onTimeClicked() }
    }
}

interface Callback {
    fun onAlertActivated(activated: Boolean)
    fun onDateClicked()
    fun onTimeClicked()
}
