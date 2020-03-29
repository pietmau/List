package com.pppp.travelchecklist.settings.dialog

enum class AppTheme(val intValue: Int) {
    DARK(0),
    LIGHT(1),
    DEFAULT(2);

    companion object {
        fun fromInt(id: Int): AppTheme? = values().associateBy(AppTheme::intValue)[id]
    }
}