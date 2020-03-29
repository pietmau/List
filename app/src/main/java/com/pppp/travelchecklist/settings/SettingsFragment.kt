package com.pppp.travelchecklist.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.settings.dialog.ThemeSettingDialogFragment
import com.pppp.travelchecklist.settings.dialog.ThemeSettingDialogFragment.Companion.THEME_KEY
import com.pppp.travelchecklist.utils.sharedPreferences
import java.lang.UnsupportedOperationException

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var themePreference: Preference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        themePreference = requireNotNull(findPreference<Preference>(getString(R.string.key_theme)))
        themePreference.setOnPreferenceClickListener {
            showThemeDialog()
        }
        setSummary(themePreference)
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    private fun setSummary(pref: Preference) {
        pref.summary = createSummary()
    }

    private fun createSummary(): String {
        return when (sharedPreferences.getInt(THEME_KEY, 0)) {
            0 -> getString(R.string.dark_theme)
            1 -> getString(R.string.light_theme)
            2 -> getString(R.string.default_theme)
            else -> throw UnsupportedOperationException("Invalid theme")
        }
    }

    private fun showThemeDialog(): Boolean {
        ThemeSettingDialogFragment().show(childFragmentManager, ThemeSettingDialogFragment.TAG)
        return true
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            THEME_KEY -> setSummary(themePreference)
        }
    }
}