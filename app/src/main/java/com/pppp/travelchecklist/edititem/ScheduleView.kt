package com.pppp.travelchecklist.edititem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.list.di.ViewCheckListModule
import com.pppp.travelchecklist.utils.appComponent
import kotlinx.android.synthetic.main.view_schedule_view.view.date
import kotlinx.android.synthetic.main.view_schedule_view.view.time
import kotlinx.android.synthetic.main.view_schedule_view.view.toggle
import kotlin.properties.Delegates

class ScheduleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {
    lateinit var callback: Callback
    lateinit var formatter: DateAndTimeFormatter
    internal var alert: Alert? = null
        set(value) {
            field = requireNotNull(value)
            setUpView(value)
        }

    private fun setUpView(value: Alert) {
        formatter.getDate(value.dateTimeInMills)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_schedule_view, this, true);
        toggle.setOnCheckedChangeListener { _, isChecked ->
            callback.onAlertActivated(requireNotNull(alert), isActivated)
        }
        date.setOnClickListener { callback.onDateClicked(requireNotNull(alert)) }
        time.setOnClickListener { callback.onTimeClicked(requireNotNull(alert)) }
    }
}

data class Alert(val dateTimeInMills: Long?, val isisAlertOn: Boolean = false)

interface Callback {
    fun onAlertActivated(alert: Alert, activated: Boolean)
    fun onDateClicked(alert: Alert)
    fun onTimeClicked(alert: Alert)
}
