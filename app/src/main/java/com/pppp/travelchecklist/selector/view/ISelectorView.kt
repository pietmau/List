package com.pppp.travelchecklist.selector.view

import com.pppp.travelchecklist.selector.presenter.SelectionData

interface ISelectorView {
    fun onError(string: String?)
    fun generateAndViewList(selection: SelectionData)
}
