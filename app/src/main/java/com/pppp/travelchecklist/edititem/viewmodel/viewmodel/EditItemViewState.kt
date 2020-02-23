package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

import com.pppp.travelchecklist.TransientEvent
import com.pppp.travelchecklist.ViewIntent
import com.pppp.travelchecklist.ViewState

data class EditItemViewState(
    val title: String,
    val description: String? = null,
    val priority: Float,
    val date: String?,
    val time: String?,
    val isAlertOn: Boolean = false
) : ViewState

sealed class EditItemViewIntent : ViewIntent {
    data class OnAlertActivated(val activated: Boolean = false) : EditItemViewIntent()
    data class DateSet(val year: Int, val monthOfYear: Int, val dayOfMonth: Int) : EditItemViewIntent()
    data class OnTimeSet(val hourOfDay: Int, val minute: Int) : EditItemViewIntent()
    data class OnDataChanged(val title: String? = null, val description: String? = null, val priority: Int? = null) : EditItemViewIntent()
    object OnDateClicked : EditItemViewIntent()
    object OnTimeClicked : EditItemViewIntent()
    object OnSaveClicked : EditItemViewIntent()
}

sealed class EditItemTransientEvent : TransientEvent {
    data class SelectDate(val timeInMills: Long) : EditItemTransientEvent()
    data class SelectTime(val timeInMills: Long) : EditItemTransientEvent()
    object SaveClicked : EditItemTransientEvent()
}