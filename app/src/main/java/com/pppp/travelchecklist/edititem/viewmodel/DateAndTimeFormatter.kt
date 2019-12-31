package com.pppp.travelchecklist.edititem.viewmodel

import java.text.SimpleDateFormat
import java.util.Date

object DateAndTimeFormatter {
    private val DATE_FORMAT = "EEE, MMM d, ''yy"

    fun getDate(dateTimeInMills: Long?): String {
        requireNotNull(dateTimeInMills)
        val date = Date(dateTimeInMills)
        return SimpleDateFormat(DATE_FORMAT).format(date)
    }
}