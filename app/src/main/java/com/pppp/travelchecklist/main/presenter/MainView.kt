package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.selector.model.Selection
import com.pppp.travelchecklist.selector.view.SelectorCallback

interface MainView {
    val selectionCallback: SelectorCallback

    fun navigateToNewList(selection: Selection)

}
