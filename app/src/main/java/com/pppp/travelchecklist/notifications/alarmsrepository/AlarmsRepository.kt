package com.pppp.travelchecklist.notifications.alarmsrepository

import com.pietrantuono.entities.TravelCheckList

interface AlarmsRepository {
    suspend fun deleteAllAlarms()

    suspend fun getAllAlarms(): List<Alarm>

    suspend fun saveAlarms(alarms: List<Alarm>)

    suspend fun getValidAlarmsFromItems(): List<Alarm>
}

data class Alarm @JvmOverloads constructor(
    var listId: String? = null,
    var catagoryId: String? = null,
    var itemId: String? = null,
    var time: Long? = null,
    var readableTime: String? = null,
    var list: String? = null
)