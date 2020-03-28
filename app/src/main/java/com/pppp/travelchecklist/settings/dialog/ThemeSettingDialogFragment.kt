package com.pppp.travelchecklist.settings.dialog

import android.app.Dialog
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.utils.sharedPreferences

class ThemeSettingDialogFragment : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listAdapter = ThemeAdapter(requireContext())
        return MaterialAlertDialogBuilder(context)
            .setTitle(R.string.choose_theme)
            .setSingleChoiceItems(listAdapter, 0) { dialog, position ->
                setPreference(requireNotNull(listAdapter.getItem(position)).theme)
                dialog.dismiss()
            }
            .create()
    }

    private fun setPreference(theme: Theme) {
        sharedPreferences.edit().run {
            putInt(THEME_KEY, theme.intValue)
            apply()
        }
    }

    companion object {
        val TAG: String = ThemeSettingDialogFragment::class.java.simpleName
        val THEME_KEY = "theme_key"
    }
}

data class ThemeContainer(val theme: Theme, val title: String) {
    override fun toString() = title
}

enum class Theme(val intValue: Int) {
    DARK(0),
    LIGHT(1),
    DEFAULT(2)
}

