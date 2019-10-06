package com.pppp.travelchecklist.main.viewmodel

import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.preferences.PreferencesWrapper

const val VISUALIZE_CHECKED_ITEMS = "visualize_checked_item"

interface SettingsUseCase {
    fun onUserChangedSettings(itemId: Int)
    fun subscribeToChanges(callback: (MainViewState.Settings) -> Unit = {})
    fun onVisualizeCheckedChanged()
    fun getCheckedVisualizePreference(): MutableMap<Int, Int>
}

class SharedPreferencesSettingsUseCase(private val preferences: PreferencesWrapper) : SettingsUseCase {

    override fun onUserChangedSettings(itemId: Int) {
        when (itemId) {
            R.id.action_show_hide_checked -> onVisualizeCheckedChanged()
            else -> TODO()
        }
    }

    override fun subscribeToChanges(callback: ((MainViewState.Settings) -> Unit)) {
        preferences.registerPreferenceChangeListener { prefs, key ->
            val map = when (key) {
                VISUALIZE_CHECKED_ITEMS -> getCheckedVisualizePreference()
                else -> mapOf<Int, Int>()
            }
            if (!map.isEmpty()) {
                callback(MainViewState.Settings(map))
            }
        }
    }

    override fun getCheckedVisualizePreference(): MutableMap<Int, Int> {
        val value = if (preferences.getBoolean(VISUALIZE_CHECKED_ITEMS)) R.string.hide_checked else R.string.show_checked
        return mutableMapOf(R.id.action_show_hide_checked to value)
    }

    override fun onVisualizeCheckedChanged() {
        preferences.setBoolean(VISUALIZE_CHECKED_ITEMS, !preferences.getBoolean(VISUALIZE_CHECKED_ITEMS))
    }
}
