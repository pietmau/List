package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.selector.presenter.SelectionData
import com.pppp.travelchecklist.selector.view.SelectorCallback

interface MainView {
    val selectionCallback: SelectorCallback

    fun navigateToNewList(selection: SelectionData)

}
