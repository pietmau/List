package com.pppp.travelchecklist.prefeerences

import android.content.SharedPreferences

private val IS_FIRST_INSTALL: String? = "first_install"

class PreferencesWrapperImpl(private val preferences: SharedPreferences) : PreferencesWrapper {

    override fun isFirstInstall(): Boolean {
        val firstInstall = preferences.getBoolean(IS_FIRST_INSTALL, true)
        preferences.edit().putBoolean(IS_FIRST_INSTALL, false).commit()
        return firstInstall
    }
}

interface PreferencesWrapper {
    fun isFirstInstall(): Boolean
}
