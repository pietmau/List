package com.pppp.travelchecklist.notifications.alarmsetter

import android.app.AlarmManager
import android.app.AlarmManager.RTC
import android.util.Log
import com.pppp.travelchecklist.notifications.alarmsetter.alarmsrepository.Alarm
import com.pppp.travelchecklist.notifications.alarmsetter.alarmsrepository.AlarmsRepository
import com.pppp.travelchecklist.notifications.alarmsetter.intentmaker.IntentMaker
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AlarmSetter @Inject constructor(
    private val intentMaker: IntentMaker,
    private val alarmsRepository: AlarmsRepository
) {

    suspend fun setAllAlarms(alarmManager: AlarmManager) {
        cancelExistingAlarms(alarmManager)
        saveAlarms()
        setAlarms(alarmManager)
    }

    private suspend fun saveAlarms() {
        alarmsRepository.deleteAllAlarms()
        val alarms = alarmsRepository.getValidAlarmsFromItems()
        alarmsRepository.saveAlarms(alarms)
    }

    private suspend fun cancelExistingAlarms(alarmManager: AlarmManager) {
        alarmsRepository.getAllAlarms().forEach { alarm ->
            val intent = intentMaker.makeAlarmIntent(requireNotNull(alarm.listId), requireNotNull(alarm.catagoryId), requireNotNull(alarm.itemId))
            alarmManager.cancel(intent)
        }
    }

    private suspend fun setAlarms(alarmManager: AlarmManager) {
        val itemsWithAlarm = alarmsRepository.getAllAlarms()
        itemsWithAlarm.forEach {
            setAlarm(it, alarmManager)
        }
    }

    private fun setAlarm(alarm: Alarm, alarmManager: AlarmManager) {
        val alertTimeInMills = alarm.time
        val pendingIntent = intentMaker.makeAlarmIntent(requireNotNull(alarm.listId), requireNotNull(alarm.catagoryId), requireNotNull(alarm.itemId))
        alarmManager.setExact(RTC, requireNotNull(alertTimeInMills), pendingIntent)
    }
}
