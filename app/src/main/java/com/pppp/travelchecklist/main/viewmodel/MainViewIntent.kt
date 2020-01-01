package com.pppp.travelchecklist.main.viewmodel

import com.pppp.travelchecklist.ViewIntent

sealed class MainViewIntent : ViewIntent {
    object NavMenuOpenSelected : MainViewIntent()
    object GoMakeNewList : MainViewIntent()
    data class NavItemSelected(val id: String) : MainViewIntent()
    data class NewListGenerated(val listId: String) : MainViewIntent()
    class OnSettingChanged(val itemId: Int) : MainViewIntent()
    object GetLatestListVisited : MainViewIntent()
    object DeleteCurrentList : MainViewIntent()
    object OnNoListFound : MainViewIntent()

}