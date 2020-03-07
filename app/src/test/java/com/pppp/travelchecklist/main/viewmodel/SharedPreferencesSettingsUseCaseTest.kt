package com.pppp.travelchecklist.main.viewmodel

import android.content.SharedPreferences
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.main.MainViewState
import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.preferences.VISUALIZE_CHECKED_ITEMS
import io.mockk.CapturingSlot
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

private const val SHOW_CHECKED = true
private const val KEY = "key"

class SharedPreferencesSettingsUseCaseTest {
    private val capturingSlot: CapturingSlot<(SharedPreferences, String) -> Unit> = slot()
    private val callback: (MainViewState.Settings) -> Unit = mockk(relaxed = true)
    private val preferences: PreferencesWrapper = mockk(relaxed = true) {
        every { getBoolean(VISUALIZE_CHECKED_ITEMS) } returns SHOW_CHECKED
    }
    private val useCase = SharedPreferencesSettingsUseCase(preferences)

    @Test
    fun `when hide or shows then saves it`() {
        // When
        useCase.onUserChangedSettings(R.id.action_show_hide_checked)

        // Then
        verify { preferences.setBoolean(VISUALIZE_CHECKED_ITEMS, !SHOW_CHECKED) }
    }

    @Test
    fun `when changes visualize checked then callback is called`() {
        // Given
        useCase.subscribeToChanges(callback)

        // When
        verify { preferences.registerPreferenceChangeListener(capture(capturingSlot)) }
        capturingSlot.captured.invoke(mockk(), VISUALIZE_CHECKED_ITEMS)

        // Then
        verify { callback.invoke(ofType()) }
    }

    @Test
    fun `when changes other key  then callback is notcalled`() {
        // Given
        useCase.subscribeToChanges(callback)

        // When
        verify { preferences.registerPreferenceChangeListener(capture(capturingSlot)) }
        capturingSlot.captured.invoke(mockk(), KEY)

        // Then
        verify(inverse = true) { callback.invoke(ofType()) }
    }
}