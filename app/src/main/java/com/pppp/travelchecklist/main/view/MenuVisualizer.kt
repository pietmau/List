package com.pppp.travelchecklist.main.view

import android.preference.PreferenceManager
import android.view.Menu
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.main.MainActivity
import com.pppp.travelchecklist.main.viewmodel.MainViewState
import com.pppp.travelchecklist.main.viewmodel.SettingsUseCase
import javax.inject.Inject

class MenuVisualizer @Inject constructor(private val settingsUseCase: SettingsUseCase) {
    private var menu: Menu? = null

    fun onCreateOptionsMenu(menu: Menu?, activity: AppCompatActivity): Boolean {
        this.menu = menu
        activity.menuInflater.inflate(R.menu.main, menu)
        updateInternal(settingsUseCase.getCheckedVisualizePreference())
        return true
    }

    fun updateMenu(settings: MainViewState.Settings) {
        val values = settings.values
        updateInternal(values)
    }

    private fun updateInternal(values: Map<Int, MenuViewState>) {
        values.entries.forEach { (key, menuViewState) ->
            menu?.findItem(key)?.apply {
                menuViewState.title?.let { setTitle(it) }
                menuViewState.icon?.let { setIcon(it) }
            }
        }
    }
}

data class MenuViewState(@StringRes val title: Int?, @DrawableRes val icon: Int?)