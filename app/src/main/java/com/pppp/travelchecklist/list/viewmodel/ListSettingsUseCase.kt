package com.pppp.travelchecklist.list.viewmodel

import android.content.ComponentCallbacks
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.pppp.travelchecklist.main.viewmodel.MainViewState
import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.preferences.PreferencesWrapperImpl
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