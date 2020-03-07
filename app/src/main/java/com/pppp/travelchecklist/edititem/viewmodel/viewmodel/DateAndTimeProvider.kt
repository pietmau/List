package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

import java.util.Calendar
import javax.inject.Inject

interface DateAndTimeProvider {
    fun getDefaultAlertTime(alertTimeInMills: Long?): Long

    fun setDate(alertTimeInMills: Long?, year: Int, monthOfYear: Int, dayOfMonth: Int): Long

    fun setTime(alertTimeInMills: Long?, hourOfDay: Int, minute: Int): Long

    fun getCurrentTimeInMills(): Long
}

class DateAndTimeProviderImpl @Inject constructor() :
    DateAndTimeProvider {

    override fun setTime(alertTimeInMills: Long?, hourOfDay: Int, minute: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        return calendar.timeInMillis
    }

    override fun getCurrentTimeInMills() = System.currentTimeMillis()

    override fun setDate(alertTimeInMills: Long?, year: Int, monthOfYear: Int, dayOfMonth: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        return calendar.timeInMillis
    }

    override fun getDefaultAlertTime(alertTimeInMills: Long?): Long {
        if (alertTimeInMills != null) {
            return alertTimeInMills
        }
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR_OF_DAY, 1)
        return calendar.timeInMillis
    }
}