package com.pppp.travelchecklist.main.view

import android.content.Context
import android.view.Menu
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.pppp.travelchecklist.R
import com.pppp.travelchecklist.main.viewmodel.Settings
import com.pppp.travelchecklist.main.viewmodel.SettingsUseCase
import javax.inject.Inject

class MenuVisualizer @Inject constructor(private val settingsUseCase: SettingsUseCase, private val context: Context) {
    private var menu: Menu? = null

    fun onCreateOptionsMenu(menu: Menu, activity: AppCompatActivity): Boolean {
        this.menu = menu
        activity.menuInflater.inflate(R.menu.main, menu)
        updateInternal(settingsUseCase.getCurrentSettings())
        return true
    }

    fun updateMenu(settings: Settings) {
        updateInternal(settings)
    }

    private fun updateInternal(settings: Settings) {
        menu?.findItem(R.id.action_show_hide_checked)?.apply {
            title = context.getString(if (settings.showCheckedItems) R.string.hide_checked else R.string.show_checked)
            val drawable = ResourcesCompat.getDrawable(
                context.resources,
                if (settings.showCheckedItems) R.drawable.ic_check_box_24px_notifcation else R.drawable.ic_check_box_outline_blank_24px, context.theme
            )
            icon = drawable
        }
    }
}

data class MenuViewState(@StringRes val title: Int?, @DrawableRes val icon: Int?)