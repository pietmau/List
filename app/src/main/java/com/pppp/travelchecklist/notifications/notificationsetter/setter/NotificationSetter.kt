package com.pppp.travelchecklist.notifications.notificationsetter.setter

import android.app.AlarmManager
import android.app.AlarmManager.RTC
import com.pppp.travelchecklist.notifications.notificationsetter.CheckListItemWithIndexes
import com.pppp.travelchecklist.notifications.notificationsetter.intentmaker.IntentMaker
import com.pppp.travelchecklist.notifications.notificationsetter.itemsprovider.ItemsProvider

class NotificationSetter(private val itemsProvider: ItemsProvider, private val intentMaker: IntentMaker) {

    fun setAllAlarms(alarmManager: AlarmManager) =
        itemsProvider.getUserItemsWithAlarm()
            .forEach {
                setAlarm(it, alarmManager)
            }

    private fun setAlarm(checkListItemWithIndexes: CheckListItemWithIndexes, alarmManager: AlarmManager) {
        val alertTimeInMills = requireNotNull(checkListItemWithIndexes.checkListItem.alertTimeInMills)
        alarmManager.setExact(RTC, alertTimeInMills, intentMaker.makeIntent(checkListItemWithIndexes))
    }
}
