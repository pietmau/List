package com.pppp.travelchecklist.settings.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.utils.sharedPreferences

class ThemeSettingDialogFragment : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listAdapter = ThemeAdapter(requireContext())
        val index = sharedPreferences.getInt(THEME_KEY, 0)
        return MaterialAlertDialogBuilder(context)
            .setTitle(R.string.choose_theme)
            .setSingleChoiceItems(listAdapter, index) { dialog, position ->
                setPreference(requireNotNull(listAdapter.getItem(position)).theme)
                dialog.dismiss()
            }
            .create()
    }

    private fun setPreference(theme: Theme) {
        sharedPreferences.edit().run {
            putInt(THEME_KEY, theme.intValue)
            commit()
        }
    }

    companion object {
        val TAG: String = ThemeSettingDialogFragment::class.java.simpleName
        val THEME_KEY = "theme_key"
    }
}
