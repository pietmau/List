package com.pppp.travelchecklist.edititem.view

import android.R
import androidx.fragment.app.Fragment
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import com.pppp.travelchecklist.utils.getColor
import java.util.Calendar

class TravelTimePickerDialog : TimePickerDialog() {

    companion object {
        fun newInstance(
            callback: OnTimeSetListener,
            fragment: Fragment,
            timeInMills: Long
        ): TimePickerDialog {
            val now = Calendar.getInstance()
            now.timeInMillis = timeInMills
            return newInstance(
                callback,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
            ).apply {
                setOkColor(fragment.getColor(R.color.white))
                setCancelColor(fragment.getColor(R.color.white))
            }
        }

        val TAG = TravelTimePickerDialog::class.simpleName!!
    }
}