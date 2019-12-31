package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

import com.pppp.travelchecklist.TransientEvent
import com.pppp.travelchecklist.ViewAction
import com.pppp.travelchecklist.ViewState

data class EditItemViewState(val title: String, val description: String? = null, val priority: Int, val date: String, val time: String) : ViewState

sealed class EditItemViewIntent : ViewAction

sealed class EditItemTransientEvent : TransientEvent