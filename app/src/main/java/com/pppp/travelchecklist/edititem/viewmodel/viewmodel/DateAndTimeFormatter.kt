package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

interface DateAndTimeFormatter {

    fun getDate(dateTimeInMills: Long?): String

    fun getTime(alertTimeInMills: Long?): String
}
