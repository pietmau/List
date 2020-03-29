package com.pppp.travelchecklist.main

import android.os.Parcel
import android.os.Parcelable
import com.pppp.travelchecklist.ViewState
import com.pppp.travelchecklist.main.view.MenuViewState
import com.pppp.travelchecklist.main.viewmodel.Settings
import com.pppp.travelchecklist.utils.replaceSameKeyItemsWith
import kotlinx.android.parcel.Parcelize

sealed class MainViewState(val settings: Settings = Settings()) : ViewState, Parcelable {
    @Parcelize
    data class NoListsPresent(val prefs: Settings = Settings()) : MainViewState(prefs)

    @Parcelize
    data class Content(val prefs: Settings = Settings(), val expandNavigation: Boolean = false) : MainViewState(prefs)

    @Parcelize
    data class Loading(val prefs: Settings = Settings()) : MainViewState(prefs)

    @Parcelize
    data class None(val prefs: Settings = Settings()) : MainViewState(prefs)

    @Parcelize
    data class LatestListNotAvailable(val prefs: Settings = Settings()) : MainViewState(prefs)

    fun withSettings(newSettings: Settings): MainViewState {
        return when (this) {
            is NoListsPresent -> this.copy(newSettings)
            is Content -> this.copy(newSettings)
            is Loading -> this.copy(newSettings)
            is None -> this.copy(newSettings)
            is LatestListNotAvailable -> this.copy(newSettings)
        }
    }
}