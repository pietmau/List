package com.pppp.travelchecklist.main.viewmodel

import android.os.Parcelable
import com.pppp.travelchecklist.ViewState
import com.pppp.travelchecklist.main.view.MenuViewState
import com.pppp.travelchecklist.utils.replaceSameKeyItemsWith
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

sealed class MainViewState(val settings: Settings = Settings()) : ViewState, Parcelable {
    @Parcelize
    data class Empty(@IgnoredOnParcel val prefs: Settings = Settings()) : MainViewState(prefs)

    @Parcelize
    data class Content(@IgnoredOnParcel val prefs: Settings = Settings(), val expandNavigation: Boolean = false) : MainViewState(prefs)

    @Parcelize
    data class Loading(@IgnoredOnParcel val prefs: Settings = Settings()) : MainViewState(prefs)

    @Parcelize
    data class None(@IgnoredOnParcel val prefs: Settings = Settings()) : MainViewState(prefs)

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

    data class Settings(val values: Map<Int, MenuViewState> = mapOf()) :Serializable
}