package com.pppp.travelchecklist.notifications.alarmsetter

import android.app.AlarmManager
import android.app.AlarmManager.RTC
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.travelchecklist.notifications.alarmsetter.intentmaker.IntentMaker
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.ItemsProvider
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class AlarmSetter @Inject constructor(
    private val itemsProvider: ItemsProvider,
    private val intentMaker: IntentMaker
) {

    suspend fun setAllAlarms(alarmManager: AlarmManager) =
        itemsProvider.getUserItemsWithAlarm()
            .forEach {
                setAlarm(it, alarmManager)
            }

    private fun setAlarm(checkListItemWithIndexes: CheckListItemWithIndexes, alarmManager: AlarmManager) {
        val alertTimeInMills = requireNotNull(checkListItemWithIndexes.checkListItem.alertTimeInMills)
        alarmManager.setExact(RTC, alertTimeInMills, intentMaker.makeIntent(checkListItemWithIndexes))
    }
}
