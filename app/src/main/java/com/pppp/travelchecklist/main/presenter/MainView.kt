package com.pppp.travelchecklist.main.presenter

import android.text.Selection
import com.pppp.travelchecklist.selector.view.SelectorCallback

interface MainView {
    val selectionCallback: SelectorCallback

    fun navigateToNewList(selection: Selection)

}
