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

class SettingsFragment : PreferenceFragmentCompat() {
    private lateinit var listener: (SharedPreferences, String) -> Unit

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        val themePreference = requireNotNull(findPreference<Preference>(getString(R.string.key_theme)))
        themePreference.setOnPreferenceClickListener {
            showThemeDialog()
        }
        setSummary(themePreference)
        listener = { _, key ->
            when (key) {
                THEME_KEY -> setSummary(themePreference)
            }
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    private fun setSummary(pref: Preference) {
        pref.summary = createSummary()
    }

    private fun createSummary(): CharSequence? {
        val theme = sharedPreferences.getInt(THEME_KEY, 0)
        return when (theme) {
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
}