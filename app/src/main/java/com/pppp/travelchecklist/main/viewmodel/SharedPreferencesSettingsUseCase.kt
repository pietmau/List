package com.pppp.travelchecklist.main.viewmodel

import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.main.view.MenuViewState
import com.pppp.travelchecklist.preferences.PreferencesWrapper
import com.pppp.travelchecklist.preferences.VISUALIZE_CHECKED_ITEMS

interface SettingsUseCase {
    fun onUserChangedSettings(itemId: Int)
    fun subscribeToChanges(callback: (MainViewState.Settings) -> Unit = {})
    fun onVisualizeCheckedChanged()
    fun getCheckedVisualizePreference(): MutableMap<Int, MenuViewState>
}

class SharedPreferencesSettingsUseCase(private val preferences: PreferencesWrapper) : SettingsUseCase {

    override fun onUserChangedSettings(itemId: Int) {
        when (itemId) {
            R.id.action_show_hide_checked -> onVisualizeCheckedChanged()
            else -> TODO()
        }
    }

    override fun subscribeToChanges(callback: ((MainViewState.Settings) -> Unit)) {
        preferences.registerPreferenceChangeListener { _, key ->
            val map = when (key) {
                VISUALIZE_CHECKED_ITEMS -> getCheckedVisualizePreference()
                else -> emptyMap<Int, MenuViewState>()
            }
            if (!map.isEmpty()) {
                callback(MainViewState.Settings(map))
            }
        }
    }

    override fun getCheckedVisualizePreference(): MutableMap<Int, MenuViewState> {
        val showCheckedItems = preferences.getBoolean(VISUALIZE_CHECKED_ITEMS)
        val title = if (showCheckedItems) R.string.hide_checked else R.string.show_checked
        val icon = if (showCheckedItems) R.drawable.ic_check_box_24px else R.drawable.ic_check_box_outline_blank_24px
        return mutableMapOf(R.id.action_show_hide_checked to MenuViewState(title, icon))
    }

    override fun onVisualizeCheckedChanged() {
        preferences.setBoolean(VISUALIZE_CHECKED_ITEMS, !preferences.getBoolean(VISUALIZE_CHECKED_ITEMS))
    }
}
