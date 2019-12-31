package com.pppp.travelchecklist.edititem

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.utils.getColor
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.util.Calendar

class TravelDatePickerDialog : DatePickerDialog() {

    companion object {

        fun newInstance(
            callback: OnDateSetListener,
            fragment: Fragment
        ): DatePickerDialog {
            val now = Calendar.getInstance()
            val newInstance = newInstance(
                callback,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            )
            newInstance.setOkColor(fragment.getColor(android.R.color.white))
            newInstance.setCancelColor(fragment.getColor(android.R.color.white))
            return newInstance
        }

        val TAG = TravelDatePickerDialog::class.simpleName!!
    }
}