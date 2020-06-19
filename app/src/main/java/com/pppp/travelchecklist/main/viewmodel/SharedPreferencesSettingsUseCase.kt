package com.pppp.travelchecklist.main.viewmodel

import android.os.Parcelable
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.main.view.MenuViewState
import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.preferences.VISUALIZE_CHECKED_ITEMS
import com.pppp.travelchecklist.settings.dialog.AppTheme
import com.pppp.travelchecklist.settings.dialog.ThemeSettingDialogFragment.Companion.THEME_KEY
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface SettingsUseCase {
    fun onUserChangedSettings(itemId: Int)
    fun onVisualizeCheckedChanged()
    fun getCheckedVisualizePreference(): MutableMap<Int, MenuViewState>
    @ExperimentalCoroutinesApi
    fun settings(): Flow<Settings>

    fun getCurrentSettings(): Settings
}

class SharedPreferencesSettingsUseCase(private val preferences: PreferencesWrapper) : SettingsUseCase {

    override fun onUserChangedSettings(itemId: Int) {
        when (itemId) {
            R.id.action_show_hide_checked -> onVisualizeCheckedChanged()
            else -> TODO()
        }
    }

    @ExperimentalCoroutinesApi
    override fun settings(): Flow<Settings> = callbackFlow {
        offer(getCurrentSettings())
        preferences.registerPreferenceChangeListener { _, key ->
            offer(getCurrentSettings())
        }
        awaitClose { cancel() }
    }

    override fun getCurrentSettings() = Settings(
        preferences.getBoolean(VISUALIZE_CHECKED_ITEMS),
        AppTheme.fromInt(preferences.getInt(THEME_KEY, 0)) ?: AppTheme.DARK
    )

    override fun getCheckedVisualizePreference(): MutableMap<Int, MenuViewState> {
        val showCheckedItems = preferences.getBoolean(VISUALIZE_CHECKED_ITEMS)
        val title = if (showCheckedItems) R.string.hide_checked else R.string.show_checked
        val icon = if (showCheckedItems) R.drawable.ic_check_box_24px_notifcation else R.drawable.ic_check_box_outline_blank_24px
        return mutableMapOf(R.id.action_show_hide_checked to MenuViewState(title, icon))
    }

    override fun onVisualizeCheckedChanged() {
        preferences.setBoolean(VISUALIZE_CHECKED_ITEMS, !preferences.getBoolean(VISUALIZE_CHECKED_ITEMS))
    }
}

@Parcelize
data class Settings(val showCheckedItems: Boolean = true, val appTheme: AppTheme = AppTheme.DARK) : Parcelable
