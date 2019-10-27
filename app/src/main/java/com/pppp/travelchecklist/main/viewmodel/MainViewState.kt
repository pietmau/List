package com.pppp.travelchecklist.main.viewmodel

import com.pppp.travelchecklist.ViewState
import com.pppp.travelchecklist.main.view.MenuViewState
import com.pppp.travelchecklist.utils.replaceSameKeyItemsWith

sealed class MainViewState(val settings: Settings = Settings(), val shouldExpandNavigation: Boolean = false) : ViewState {
    data class Empty(val prefs: Settings = Settings()) : MainViewState(prefs)
    data class Content(val prefs: Settings = Settings(), val expandNavigation: Boolean = false) : MainViewState(prefs)
    data class Loading(val prefs: Settings = Settings()) : MainViewState(prefs)
    data class None(val prefs: Settings = Settings()) : MainViewState(prefs)

    fun makeCopyReplacingOldSettingsWithNew(oldSettings: Settings?): MainViewState {
        val values = oldSettings?.values?.replaceSameKeyItemsWith(newElements = settings.values) ?: mapOf()
        return withNewSettings(Settings(values))
    }

    fun withNewSettings(newSettings: Settings): MainViewState {
        return when (this) {
            is Empty -> this.copy(newSettings)
            is Content -> this.copy(newSettings)
            is Loading -> this.copy(newSettings)
            is None -> this.copy(newSettings)
        }
    }

    data class Settings(val values: Map<Int, MenuViewState> = mapOf())
}