package com.pppp.travelchecklist.preferences

import android.content.SharedPreferences

private const val IS_FIRST_INSTALL: String = "first_install"
const val VISUALIZE_CHECKED_ITEMS = "visualize_checked_item"

class PreferencesWrapperImpl(private val preferences: SharedPreferences) : PreferencesWrapper, SharedPreferences.OnSharedPreferenceChangeListener {
    private var callback: (preferences: SharedPreferences, key: String) -> Unit = { _, _ -> }

    init {
        preferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun isFirstInstall(): Boolean {
        val firstInstall = preferences.getBoolean(IS_FIRST_INSTALL, true)
        preferences.edit().putBoolean(IS_FIRST_INSTALL, false).apply()
        return firstInstall
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        callback(sharedPreferences, key)
    }

    override fun registerPreferenceChangeListener(callback: ((preferences: SharedPreferences, key: String) -> Unit)) {
        this.callback = callback
    }

    override fun getBoolean(key: String) = preferences.getBoolean(key, false)

    override fun setBoolean(key: String, value: Boolean) = preferences.edit().putBoolean(key, value).apply()

    override fun getInt(key: String, defaultValue: Int) = preferences.getInt(key, defaultValue)

}

interface PreferencesWrapper {
    fun isFirstInstall(): Boolean
    fun registerPreferenceChangeListener(callback: ((preferences: SharedPreferences, key: String) -> Unit) = { _, _ -> })
    fun getBoolean(key: String): Boolean
    fun setBoolean(key: String, value: Boolean)
    fun getInt(themeKey: String, defaultValue: Int = 0): Int
}