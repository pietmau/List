package com.pppp.travelchecklist.list.viewmodel

import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.preferences.VISUALIZE_CHECKED_ITEMS

class ListSettingsUseCase(private val preferenceManager: PreferencesWrapper) {

    fun registerVisualizationPreferencesListener(callback: (showCheced: Boolean) -> Unit = {}) {
        preferenceManager.registerPreferenceChangeListener { prefs, key ->
            when (key) {
                VISUALIZE_CHECKED_ITEMS -> callback(getShowCheckedPreferences())
                else -> mapOf<Int, Int>()
            }
        }
    }

    fun getShowCheckedPreferences() = preferenceManager.getBoolean(VISUALIZE_CHECKED_ITEMS)
}