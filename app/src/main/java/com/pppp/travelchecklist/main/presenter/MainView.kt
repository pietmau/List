package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.newlist.view.NewListCallback

interface MainView {
    val selectionCallback: NewListCallback?

    fun navigateToNewList(selection: String)

    fun onError(string: String)

}
