package com.pppp.travelchecklist.main.viewmodel

import androidx.annotation.IdRes
import com.pppp.travelchecklist.ViewAction

sealed class MainViewAction : ViewAction {
    object NavMenuOpenSelected : MainViewAction()
    object GoMakeNewList : MainViewAction()
    data class NavItemSelected(val id: String) : MainViewAction()
    data class NewListGenerated(val listId: String) : MainViewAction()
    class OnSettingChanged(val itemId: Int) : MainViewAction()
    object GetLatestListVisited : MainViewAction()
    object DeleteCurrentList : MainViewAction()
}