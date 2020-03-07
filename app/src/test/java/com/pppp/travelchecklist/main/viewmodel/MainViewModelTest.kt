package com.pppp.travelchecklist.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.analytics.MainAnalyticsLogger
import com.pppp.travelchecklist.main.MainTransientEvent
import com.pppp.travelchecklist.main.MainViewIntent
import com.pppp.travelchecklist.main.MainViewState
import io.mockk.CapturingSlot
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test

private const val ID = "id"
private const val SETTING_ITEM = 123

class MainViewModelTest {
    private val mainUseCase: MainUseCase = mockk(relaxed = true)
    private val settingsUseCase: SettingsUseCase = mockk(relaxed = true)
    private val analytics: MainAnalyticsLogger = mockk(relaxed = true)
    private val transientEvents: MutableLiveData<MainTransientEvent> = mockk(relaxed = true)
    private val internalStates: MutableLiveData<MainViewState> = mockk(relaxed = true){
        every { value } returns MainViewState.None()
    }
    private lateinit var model: MainViewModel
    private val onListRetrieved: CapturingSlot<(List<TravelCheckListImpl>, String?) -> Unit> = slot()
    private val onError: CapturingSlot<(Throwable?) -> Unit> = slot()

    @Before
    fun setUp() {
        model = MainViewModel(mainUseCase, settingsUseCase, analytics, transientEvents, internalStates)
        model.states
        val slot: CapturingSlot<(MainViewState.Settings) -> Unit> = slot()
        verify { settingsUseCase.subscribeToChanges(capture(slot)) }
        slot.captured(MainViewState.Settings())
    }

    @Test
    fun `when starts queries settings`() {
        // Then
        verify { settingsUseCase.subscribeToChanges(ofType()) }
        confirmVerified(settingsUseCase)
    }

    @Test
    fun `when settings change then emits`() {
        // Then
        verifyPostViewState<MainViewState.None>()
    }

    @Test
    fun `when emits NavMenuOpenSelected then emits analytics`() {
        // When
        acceptNavMenuOpenSelected()

        // Then
        verify { analytics.onMainMenuOpen() }
        confirmVerified(analytics)
    }

    @Test
    fun `emits NavMenuOpenSelected then queries usecase`() {
        acceptNavMenuOpenSelected()
    }

    @Test
    fun `when emits gets last visited then emits error`() {
        // When
        acceptNavMenuOpenSelected()
        onListRetrieved.captured(mockk(), ID)

        // Then
        verifyTransientEventEmitted<MainTransientEvent.OpenNavMenu>()
    }

    @Test
    fun `when emits gets error then emits error`() {
        // When
        acceptNavMenuOpenSelected()
        onError.captured(mockk(relaxed = true))

        // Then
        verifyTransientEventEmitted<MainTransientEvent.Error>()
    }

    @Test
    fun `when receives gets last visited then emits error`() {
        // When
        model.accept(MainViewIntent.GetLatestListVisited)

        // Then
        verify { analytics.getLatestListVisited() }
        confirmVerified(analytics)
    }

    @Test
    fun `when receives get last visited then emits loading`() {
        // When
        model.accept(MainViewIntent.GetLatestListVisited)

        // Then
        verifyPostViewState<MainViewState.Loading>()
    }

    @Test
    fun `when receives get last visited then gets last visited`() {
        // When
        model.accept(MainViewIntent.GetLatestListVisited)

        // Then
        verify(exactly = 1) { mainUseCase.getLastVisitedList(capture(onError), capture(onListRetrieved)) }
        confirmVerified(mainUseCase)
    }

    @Test
    fun `when receives NavItemSelected then goes to list`() {
        // When
        model.accept(MainViewIntent.NavItemSelected(ID))

        // Then
        verify { analytics.goToList(ID) }
        confirmVerified(analytics)
        verify { mainUseCase.saveLastVisitedList(ID) }
        confirmVerified(mainUseCase)
        verifyPostViewState<MainViewState.Content>()
        verifyTransientEventEmitted<MainTransientEvent.GoToList>()
    }

    @Test
    fun `when receives NavMenuOpenSelected then opens menu`() {
        // When
        model.accept(MainViewIntent.NavMenuOpenSelected)

        // Then
        verify { analytics.onMainMenuOpen() }
        confirmVerified(analytics)
        verify { mainUseCase.getLastVisitedList(capture(onError), capture(onListRetrieved)) }
        confirmVerified(mainUseCase)
    }

    @Test
    fun `when receives NewListGenerated then goes to list`() {
        // When
        model.accept(MainViewIntent.NewListGenerated(ID))

        // Then
        verify { analytics.goToList(ID) }
        confirmVerified(analytics)
        verify { mainUseCase.saveLastVisitedList(ID) }
        confirmVerified(mainUseCase)
        verifyPostViewState<MainViewState.Content>()
        verifyTransientEventEmitted<MainTransientEvent.GoToList>()
    }

    @Test
    fun `when receives GoMakeNewList then creates new list`() {
        // When
        model.accept(MainViewIntent.GoMakeNewList)

        // Then
        verify { analytics.goToCreateNewList() }
        confirmVerified(analytics)
        verifyTransientEventEmitted<MainTransientEvent.GoToCreateNewList>()
    }

    @Test
    fun `when receives OnSettingChanged then changes settings`() {
        // When
        model.accept(MainViewIntent.OnSettingChanged(SETTING_ITEM))

        // Then
        verify { settingsUseCase.onUserChangedSettings(SETTING_ITEM) }
    }

    @Test
    fun `when receives DeleteCurrentList then deletes it`() {
        // When
        acceptDeleteCurrentList()

        // Then
        verify { mainUseCase.deleteCurrentList() }
    }

    @Test
    fun `when receives DeleteCurrentList and is empty then emits Empty`() {
        // Given
        every { mainUseCase.isEmpty() } returns true
        // When
        acceptDeleteCurrentList()

        // Then
        verifyPostViewState<MainViewState.NoListsPresent>()
    }

    @Test
    fun `when receives DeleteCurrentList and is not empty then emits Content`() {
        // Given
        every { mainUseCase.isEmpty() } returns false
        // When
        acceptDeleteCurrentList()

        // Then
        verifyPostViewState<MainViewState.Content>()
    }

    @Test
    fun `when receives OnNoListFound and is not empty then emits Content`() {
        // When
        model.accept(MainViewIntent.OnNoListFound)

        // Then
        verifyPostViewState<MainViewState.NoListsPresent>()
    }

    private fun acceptDeleteCurrentList() {
        model.accept(MainViewIntent.DeleteCurrentList)
    }

    private inline fun <reified T : MainViewState> verifyPostViewState() {
        verify { internalStates.postValue(ofType(T::class)) }
    }

    private fun acceptNavMenuOpenSelected() {
        model.accept(MainViewIntent.NavMenuOpenSelected)
        verify { mainUseCase.getLastVisitedList(capture(onError), capture(onListRetrieved)) }
        confirmVerified(mainUseCase)
    }

    private inline fun <reified T : MainTransientEvent> verifyTransientEventEmitted() {
        verify { transientEvents.postValue(ofType<T>()) }
        confirmVerified(transientEvents)
    }
}