package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

interface DateAndTimeProvider {
    fun getDefaultAlertTime(alertTimeInMills: Long?): Long

    fun setDate(alertTimeInMills: Long?, year: Int, monthOfYear: Int, dayOfMonth: Int): Long

    fun setTime(alertTimeInMills: Long?, hourOfDay: Int, minute: Int): Long
}


