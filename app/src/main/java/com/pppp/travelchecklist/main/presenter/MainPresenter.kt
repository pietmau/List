package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.selector.view.SelectorView
import com.pppp.travelchecklist.selector.view.model.Selection


class MainPresenter : SelectorView.Callback {
    private var mainActivity: MainView? = null

    override fun onPageChanged(position: Int) {

    }

    override fun onFinishClicked(selection: Selection) {
        mainActivity?.navigateToNewList(selection)
    }

    fun bind(mainActivity: MainView) {
        this.mainActivity = mainActivity
    }

    fun unbind() {
        mainActivity = null
    }
}