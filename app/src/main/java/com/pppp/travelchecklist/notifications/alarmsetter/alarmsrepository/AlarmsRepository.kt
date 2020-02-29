package com.pppp.travelchecklist.notifications.alarmsetter.alarmsrepository

import com.google.android.gms.tasks.Task
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.TravelCheckList
import java.text.SimpleDateFormat

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