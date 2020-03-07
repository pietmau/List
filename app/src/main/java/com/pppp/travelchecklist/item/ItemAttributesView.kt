package com.pppp.travelchecklist.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.travelchecklist.R
import kotlinx.android.synthetic.main.custom_item_attrs_view.view.alarm
import kotlinx.android.synthetic.main.custom_item_attrs_view.view.description
import kotlinx.android.synthetic.main.custom_item_attrs_view.view.priority_flag

class ItemAttributesView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_item_attrs_view, this, true)
        orientation = HORIZONTAL
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    }

    fun setData(checkListItem: CheckListItemImpl) {
        setUpPriority(checkListItem.priority)
        setUpDescription(checkListItem.description)
        setAlarm(checkListItem.isAlertOn, checkListItem.alertTimeInMills ?: 0)
    }

    private fun setAlarm(isAlertOn: Boolean, alertTimeInMills: Long) {
        alarm.visibility = if (!isAlertOn) GONE else VISIBLE
        alarm.imageTintList = getColor(alertTimeInMills)
    }

    private fun getColor(alertTimeInMills: Long) =
        if (isAlertExpired(alertTimeInMills)) ResourcesCompat.getColorStateList(
            resources,
            android.R.color.holo_red_light,
            context.theme
        ) else ResourcesCompat.getColorStateList(resources, R.color.gray, context.theme)

    private fun isAlertExpired(alertTimeInMills: Long) = alertTimeInMills < System.currentTimeMillis()

    private fun setUpDescription(itemDescription: String?) {
        description.visibility = if (itemDescription.isNullOrBlank()) GONE else VISIBLE
    }

    private fun setUpPriority(priority: Int) {
        priority_flag.visibility = if (priority > 0) VISIBLE else GONE
        priority_flag.setFlag(priority)
    }
}