package com.pppp.travelchecklist.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.Preference.OnPreferenceClickListener
import androidx.preference.PreferenceFragmentCompat
import com.pppp.travelchecklist.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        findPreference<Preference>(getString(R.string.key_theme))?.onPreferenceClickListener = OnPreferenceClickListener { _ -> showThemeSelectonfDialog() }
    }

    private fun showThemeSelectonfDialog(): Boolean {
        return true
    }
}