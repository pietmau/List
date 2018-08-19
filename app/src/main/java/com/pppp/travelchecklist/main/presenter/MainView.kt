package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.selector.model.Selection

interface MainView {
    fun navigateToNewList(selection: Selection)

}
