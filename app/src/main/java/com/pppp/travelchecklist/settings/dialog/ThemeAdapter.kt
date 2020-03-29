package com.pppp.travelchecklist.settings.dialog

import android.content.Context
import android.widget.ArrayAdapter
import com.pppp.travelchecklist.R

class ThemeAdapter(ctx: Context) : ArrayAdapter<ThemeContainer>(ctx, android.R.layout.simple_list_item_single_choice) {

    init {
        addAll(themes())
    }

    private fun themes(): List<ThemeContainer> {
        return listOf(
            ThemeContainer(
                AppTheme.DARK,
                context.getString(R.string.dark_theme)
            ),
            ThemeContainer(
                AppTheme.LIGHT,
                context.getString(R.string.light_theme)
            ),
            ThemeContainer(
                AppTheme.DEFAULT,
                context.getString(R.string.default_theme)
            )
        )
    }
}
