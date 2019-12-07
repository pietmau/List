package com.pppp.travelchecklist.main.viewmodel

import com.pppp.travelchecklist.ViewAction

sealed class MainViewAction : ViewAction {
    object NavMenuOpenSelected : MainViewAction()
    object GoMakeNewList : MainViewAction()
    data class NavItemSelected(val id: Long) : MainViewAction()
    data class NewListGenerated(val listId: Long) : MainViewAction()
    class OnSettingChanged(val itemId: Int) : MainViewAction()
    object GetLatestListVisited : MainViewAction()
    object DeleteCurrentList : MainViewAction()
    object OnNoListFound : MainViewAction()

}