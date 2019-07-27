package com.pppp.travelchecklist.newlist.view

interface NewListView {
    fun onError(string: String)

    fun onListGenerated(checkListId: String)

    fun generatingList()
}
