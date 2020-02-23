package com.pppp.travelchecklist.notifications.alarmsetter

import android.app.AlarmManager
import android.app.AlarmManager.RTC
import com.pppp.travelchecklist.notifications.alarmsetter.intentmaker.AlarmIntentMaker
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.ItemsProvider
import javax.inject.Inject

class AlarmSetter @Inject constructor(
    private val itemsProvider: ItemsProvider,
    private val alarmIntentMaker: AlarmIntentMaker
) {

    suspend fun setAllAlarms(alarmManager: AlarmManager) {
        cancelExistingAlarms(alarmManager)
        setAlarms(alarmManager)
    }

    private fun cancelExistingAlarms(alarmManager: AlarmManager) = alarmManager.cancel(alarmIntentMaker.makeEmptyAlarmIntent())

    private suspend fun setAlarms(alarmManager: AlarmManager) {
        val itemsWithAlarm = itemsProvider.getUserItemsWithAlarm()
        itemsWithAlarm.forEach { setAlarm(it, alarmManager) }
    }

    private fun setAlarm(checkListItemWithIndexes: CheckListItemWithIndexes, alarmManager: AlarmManager) {
        val alertTimeInMills = requireNotNull(checkListItemWithIndexes.checkListItem.alertTimeInMills)
        alarmManager.setExact(RTC, alertTimeInMills, alarmIntentMaker.makeAlarmIntent(checkListItemWithIndexes))
    }
}
