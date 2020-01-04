package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

import java.util.Calendar

class DateAndTimeProviderImpl : DateAndTimeProvider {

    override fun setTime(alertTimeInMills: Long?, hourOfDay: Int, minute: Int): Long {
        val mills = getDefaultAlertTime(alertTimeInMills)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = mills
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        return calendar.timeInMillis
    }

    override fun setDate(alertTimeInMills: Long?, year: Int, monthOfYear: Int, dayOfMonth: Int): Long {
        val mills = getDefaultAlertTime(alertTimeInMills)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = mills
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