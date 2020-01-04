package com.pppp.travelchecklist.main.viewmodel

import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.main.model.MainModel
import io.mockk.CapturingSlot
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

private const val ID = "id"

class FirebaseMainUseCaseTest {
    private val list: List<TravelCheckListImpl> = emptyList()
    private val success: (List<TravelCheckListImpl>, String?) -> Unit = mockk(relaxed = true)
    private val failure: (Throwable?) -> Unit = mockk(relaxed = true)
    private val successCaptor: CapturingSlot<((String?) -> Unit)> = slot()
    private val failureCaptor: CapturingSlot<(Throwable?) -> Unit> = slot()
    private val mainModel: MainModel = mockk(relaxed = true) {
        every { checkLists } returns mockk() {
            every { values } returns list
        }
    }
    private val useCase = FirebaseMainUseCase(mainModel)

    @Before
    fun setUp() {
    }

    @Test
    fun `when deletes then deletes`() {
        // When
        useCase.deleteCurrentList()

        // Then
        verify { mainModel.deleteCurrentList() }
        confirmVerified(mainModel)
    }

    @Test
    fun `when save then saves`() {
        // When
        useCase.saveLastVisitedList(ID)

        // Then
        verify { mainModel.saveLastVisitedList(ID) }
        confirmVerified(mainModel)
    }

    @Test
    fun `when getLastVisitedList then getLastVisitedList`() {
        // When
        useCase.getLastVisitedList(failure, success)

        // Then
        verify { mainModel.getLastVisitedList(isNull(inverse = true), capture(successCaptor)) }
        confirmVerified(mainModel)
    }

    @Test
    fun `when success then calls success`() {
        // Given
        useCase.getLastVisitedList(failure, success)

        // When
        verify { mainModel.getLastVisitedList(capture(failureCaptor), capture(successCaptor)) }
        successCaptor.captured(ID)

        // Then
        verify { success.invoke(list, ID) }
    }
}