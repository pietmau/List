package com.pppp.travelchecklist.menu

import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import com.pppp.travelchecklist.R

data class BetterMenuItem(
    val type: ViewType = ViewType.NORMAL,
    val id: String,
    val title: String,
    val subtitle: String? = null,
    @DrawableRes
    val icon: Int? = null,
    val selected: Boolean = false
)