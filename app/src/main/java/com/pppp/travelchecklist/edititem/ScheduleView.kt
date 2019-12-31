package com.pppp.travelchecklist.edititem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.view_schedule_view.view.date
import kotlinx.android.synthetic.main.view_schedule_view.view.time
import kotlinx.android.synthetic.main.view_schedule_view.view.toggle

class ScheduleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {
    lateinit var callback: Callback
    lateinit var alert: Alert

    init {
        LayoutInflater.from(context).inflate(R.layout.view_schedule_view, this, true);
        toggle.setOnCheckedChangeListener { _, isChecked ->
            callback.onAlertActivated(alert, isActivated)
        }
        date.setOnClickListener { callback.onDateClicked(alert) }
        time.setOnClickListener { callback.onTimeClicked(alert) }
    }
}

data class Alert(val dateTimeInMills: Long?, val isisAlertOn: Boolean = false)

interface Callback {
    fun onAlertActivated(alert: Alert, activated: Boolean)
    fun onDateClicked(alert: Alert)
    fun onTimeClicked(alert: Alert)
}
