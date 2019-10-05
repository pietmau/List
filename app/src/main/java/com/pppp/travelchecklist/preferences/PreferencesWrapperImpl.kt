package com.pppp.travelchecklist.preferences

import android.content.SharedPreferences

private val IS_FIRST_INSTALL: String? = "first_install"

class PreferencesWrapperImpl(private val preferences: SharedPreferences) : PreferencesWrapper {
    lateinit var function: (sharedPreferences: SharedPreferences, key: String) -> Unit

    override fun isFirstInstall(): Boolean {
        val firstInstall = preferences.getBoolean(IS_FIRST_INSTALL, true)
        preferences.edit().putBoolean(IS_FIRST_INSTALL, false).apply()
        return firstInstall
    }

    override fun registerPreferenceChangeListener(callback: ((preferences: SharedPreferences, key: String) -> Unit)) {
        function = { sharedPreferences, key ->
            callback(sharedPreferences, key)
        }
        preferences.registerOnSharedPreferenceChangeListener(function)
    }

    override fun getBoolean(key: String) = preferences.getBoolean(key, false)

    override fun setBoolean(key: String, value: Boolean) = preferences.edit().putBoolean(key, value).apply()

}

interface PreferencesWrapper {
    fun isFirstInstall(): Boolean
    fun registerPreferenceChangeListener(callback: ((preferences: SharedPreferences, key: String) -> Unit) = { _, _ -> })
    fun getBoolean(key: String): Boolean
    fun setBoolean(key: String, value: Boolean)

}