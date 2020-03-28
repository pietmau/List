package com.pppp.travelchecklist.settings

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.menu.BetterMenuItem
import kotlinx.android.synthetic.main.fragment_dialog_settings.settings_menu

private const val SELECT_THEME = "select_theme"

class SettingsBottomDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_dialog_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        settings_menu.callback = ::onItemSelected
        settings_menu.items = listOf(BetterMenuItem(id = SELECT_THEME, title = getString(R.string.choose_theme)))
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val vv = sharedPreferences.getInt(SELECT_THEME, 0)
    }

    private fun onItemSelected(item: BetterMenuItem) {
        when (item.id) {
            SELECT_THEME -> Unit
        }
    }

    companion object {
        val TAG = requireNotNull(SettingsBottomDialogFragment::class.simpleName)
    }
}