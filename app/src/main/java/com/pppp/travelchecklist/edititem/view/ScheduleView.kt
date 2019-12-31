package com.pppp.travelchecklist.edititem.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.edititem.viewmodel.DateAndTimeFormatter
import kotlinx.android.synthetic.main.view_schedule_view.view.container
import kotlinx.android.synthetic.main.view_schedule_view.view.date
import kotlinx.android.synthetic.main.view_schedule_view.view.time
import kotlinx.android.synthetic.main.view_schedule_view.view.toggle

class ScheduleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {
    internal var callback: Callback? = null
    lateinit var formatter: DateAndTimeFormatter
    internal var alert: Alert? = null
        set(value) {
            field = requireNotNull(value)
            setUpView(value)
        }

    private fun setUpView(alert: Alert) {
        date.text = formatter.getDate(alert.dateTimeInMills)
        setDateAndTimeVisibility(alert.isisAlertOn)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_schedule_view, this, true);
        toggle.setOnCheckedChangeListener { _, isChecked ->
            callback?.onAlertActivated(isActivated)
            setDateAndTimeVisibility(isChecked)
        }
        date.setOnClickListener { callback?.onDateClicked(requireNotNull(alert)) }
        time.setOnClickListener { callback?.onTimeClicked(requireNotNull(alert)) }
    }

    private fun setDateAndTimeVisibility(isChecked: Boolean) {
        container.visibility = if (isChecked) View.VISIBLE else View.INVISIBLE
    }
}

data class Alert(val dateTimeInMills: Long?, val isisAlertOn: Boolean = false)

interface Callback {
    fun onAlertActivated(activated: Boolean)
    fun onDateClicked(alert: Alert)
    fun onTimeClicked(alert: Alert)
}
