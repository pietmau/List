package com.pppp.travelchecklist.main.viewmodel

import com.pppp.travelchecklist.createlist.view.NewListCallback

interface ErrorCallback {

    fun onError(string: String)
}

interface CreateChecklistView : ErrorCallback {

    val selectionCallback: NewListCallback?

    fun navigateToSelector()

    fun navigateToNewList(selection: String)

}
