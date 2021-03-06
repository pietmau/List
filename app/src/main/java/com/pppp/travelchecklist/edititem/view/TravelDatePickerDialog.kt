package com.pppp.travelchecklist.edititem.view

import android.R
import androidx.fragment.app.Fragment
import com.pppp.travelchecklist.utils.getColor
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.util.Calendar

class TravelDatePickerDialog : DatePickerDialog() {

    companion object {

        fun newInstance(
            callback: OnDateSetListener,
            fragment: Fragment,
            timeInMills: Long
        ): DatePickerDialog {
            val now = Calendar.getInstance()
            now.timeInMillis = timeInMills
            return newInstance(
                callback,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            ).apply {
                setOkColor(fragment.getColor(R.color.white))
                setCancelColor(fragment.getColor(R.color.white))
            }
        }

        val TAG = TravelDatePickerDialog::class.simpleName!!
    }
}