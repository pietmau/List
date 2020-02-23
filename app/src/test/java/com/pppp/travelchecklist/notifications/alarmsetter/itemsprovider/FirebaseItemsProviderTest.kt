package com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider

import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.notifications.alarmsetter.CATEGORY_ID
import com.pppp.travelchecklist.notifications.alarmsetter.ITEM_ID
import com.pppp.travelchecklist.notifications.alarmsetter.LIST_ID
import com.pppp.travelchecklist.notifications.alarmsetter.TIME
import com.pppp.travelchecklist.utils.TimeProvider
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

private const val TIME_PLUS_ONE = TIME + 1
private const val TIME_MINUS_ONE = TIME - 1

class FirebaseItemsProviderTest {
    private val repo: UserCheckListsRepository = mockk()
    private val timeProvider: TimeProvider = mockk()
    private val firebaseItemsProvider = FirebaseItemsProvider(repo, timeProvider)

    @Before
    fun setUp() {
        every { timeProvider.getCurrentTimeInMills() } returns TIME
    }

    @Test
    fun `when all good emits right item`() {
        // Given
        coEvery { repo.getUserCheckLists() } returns listOf(createList())

        // Then
        runBlocking {
            val actual = firebaseItemsProvider.getUserItemsWithAlarm()
            assertThat(actual).isNotEmpty
            assertThat(actual[0].categoryId).isEqualTo(CATEGORY_ID)
            assertThat(actual[0].listId).isEqualTo(LIST_ID)
            assertThat(actual[0].checkListItem).isEqualTo(CheckListItemImpl(alertTimeInMills = TIME_PLUS_ONE, id = ITEM_ID, isAlertOn = true))
        }
    }

    @Test
    fun `when in the past does not emit`() {
        // Given
        coEvery { repo.getUserCheckLists() } returns listOf(createList(time = TIME_MINUS_ONE))

        // Then
        runBlocking {
            assertThat(firebaseItemsProvider.getUserItemsWithAlarm()).isEmpty()
        }
    }

    @Test
    fun `when alarm not set does not emit`() {
        // Given
        coEvery { repo.getUserCheckLists() } returns listOf(createList(alarmset = false))

        // Then
        runBlocking {
            assertThat(firebaseItemsProvider.getUserItemsWithAlarm()).isEmpty()
        }
    }

    @Test
    fun `when alarm time null not set does not emit`() {
        // Given
        coEvery { repo.getUserCheckLists() } returns listOf(createList(time = null))

        // Then
        runBlocking {
            assertThat(firebaseItemsProvider.getUserItemsWithAlarm()).isEmpty()
        }
    }

    private fun createList(
        listId: String? = LIST_ID,
        categoryId: String = CATEGORY_ID,
        itemId: String = ITEM_ID,
        time: Long? = TIME_PLUS_ONE,
        alarmset: Boolean = true
    ) =
        TravelCheckListImpl(
            id = listId,
            categories = listOf(CategoryImpl(id = categoryId, items = listOf(CheckListItemImpl(id = itemId, isAlertOn = alarmset, alertTimeInMills = time))))
        )
}
