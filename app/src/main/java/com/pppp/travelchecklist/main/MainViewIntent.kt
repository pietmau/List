package com.pppp.travelchecklist.main

import com.pppp.travelchecklist.ViewIntent

sealed class MainViewIntent : ViewIntent {
    object NavMenuOpenSelected : MainViewIntent()
    object GoMakeNewList : MainViewIntent()
    data class NavItemSelected(val id: String) : MainViewIntent()
    data class NewListGenerated(val listId: String) : MainViewIntent()
    class OnSettingChanged(val itemId: Int) : MainViewIntent()
    data class GetLatest(val path: List<String>) : MainViewIntent()
    object DeleteCurrentList : MainViewIntent()
    object OnNoListFound : MainViewIntent()

}