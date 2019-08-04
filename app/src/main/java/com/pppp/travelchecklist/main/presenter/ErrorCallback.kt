package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.newlist.view.NewListCallback

interface ErrorCallback {

    fun onError(string: String)

}

interface CreateChecklistView : ErrorCallback {

    val selectionCallback: NewListCallback?

    fun navigateToSelector()

    fun navigateToNewList(selection: String)

}
