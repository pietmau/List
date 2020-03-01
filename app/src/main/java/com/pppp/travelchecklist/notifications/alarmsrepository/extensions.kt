package com.pppp.travelchecklist.notifications.alarmsrepository

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.TravelCheckList
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal fun TravelCheckList.getValidAlarms(currentTime: Long): List<Alarm> {
    val dateFormat = SimpleDateFormat("MM-dd' 'HH:mm:ss.SSSXXX", Locale.UK)

    fun toAlarm(travelCheckList: TravelCheckList, it: CheckListItem, category: Category): Alarm {
        val listId = requireNotNull(travelCheckList.id)
        val time = requireNotNull(it.alertTimeInMills)
        val readableTime = dateFormat.format(Date(requireNotNull(it.alertTimeInMills)))
        return Alarm(listId = listId, catagoryId = category.id, itemId = it.id, time = time, readableTime = readableTime, list = travelCheckList.name)
    }

    return categories.flatMap { category ->
        category.items.getValidAlarms(currentTime).map { item -> toAlarm(this, item, category) }
    }
}

internal fun List<CheckListItem>.getValidAlarms(currentTime: Long) =
    filter { it.isAlertOn }
        .filterNot { it.alertTimeInMills == null }
        .filterNot {
            val
                inThePast = it.isInThePast(currentTime)
            inThePast
        }

internal fun CheckListItem.isInThePast(now: Long) = alertTimeInMills ?: 0 < now
