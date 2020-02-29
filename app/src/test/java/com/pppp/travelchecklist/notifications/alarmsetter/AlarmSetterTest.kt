package com.pppp.travelchecklist.notifications.alarmsetter

import android.app.AlarmManager
import android.app.AlarmManager.RTC
import android.app.PendingIntent
import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.travelchecklist.notifications.alarmsetter.intentmaker.IntentMaker
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.ItemsProvider
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

const val LIST_ID: String = "listId"
const val CATEGORY_ID: String = "categoryId"
const val ITEM_ID: String = "itemId"
const val TIME: Long = 123456

class AlarmSetterTest {
    private val intent: PendingIntent = mockk()
    private val itemsProvider: ItemsProvider = mockk()
    private val intentMaker: IntentMaker = mockk()
    private val alarmSetter = AlarmSetter(itemsProvider, intentMaker)
    private val checkListItem: CheckListItem = CheckListItemImpl(id = ITEM_ID, alertTimeInMills = TIME)
    private val alarmManager: AlarmManager = mockk(relaxed = true)
    private val list = listOf(CheckListItemWithIndexes(LIST_ID, CATEGORY_ID, checkListItem))

    @Before
    fun setUp() {
        coEvery { itemsProvider.getUserItemsWithAlarm() } returns list
        every { intentMaker.makeAlarmIntent(any(), alarm.catagoryId, alarm.itemId) } returns intent
    }

    @Test
    fun `when there are items than sets alarms`() = runBlocking {
        // When
        alarmSetter.setAllAlarms(alarmManager)

        // Then
        verify { alarmManager.setExact(eq(RTC), eq(TIME), eq(intent)) }
    }
}