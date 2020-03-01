package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.utils.ResourcesWrapper
import java.text.SimpleDateFormat
import java.util.Date

interface DateAndTimeFormatter {

    fun getDate(dateTimeInMills: Long?): String

    fun getTime(alertTimeInMills: Long?): String
}

class DateAndTimeFormatterImpl(private val resources: ResourcesWrapper) :
    DateAndTimeFormatter {
    private val DATE_FORMAT = "EEE, MMM d, ''yy"
    private val TIME_FORMAT = "h:mm a"

    override fun getDate(dateTimeInMills: Long?): String {
        if (dateTimeInMills == null) {
            return resources.getString(R.string.na)
        }
        val date = Date(dateTimeInMills)
        return SimpleDateFormat(DATE_FORMAT).format(date)
    }

    override fun getTime(dateTimeInMills: Long?): String {
        if (dateTimeInMills == null) {
            return resources.getString(R.string.na)
        }
        val date = Date(dateTimeInMills)
        return SimpleDateFormat(TIME_FORMAT).format(date)
    }
}