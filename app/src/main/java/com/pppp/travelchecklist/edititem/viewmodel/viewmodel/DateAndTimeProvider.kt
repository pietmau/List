package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

interface DateAndTimeProvider {
    fun getDefaultAlertTime(alertTimeInMills: Long?): Long

    fun onDateSet(alertTimeInMills: Long?, year: Int, monthOfYear: Int, dayOfMonth: Int): Long

    fun onTimeSet(alertTimeInMills: Long?, hourOfDay: Int, minute: Int): Long
}


