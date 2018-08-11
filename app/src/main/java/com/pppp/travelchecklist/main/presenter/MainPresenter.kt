package com.pppp.travelchecklist.main.presenter

import com.pppp.travelchecklist.selector.view.SelectorCallback


class MainPresenter : SelectorCallback {
    private var mainActivity: MainView? = null

    override fun onFinishClicked() {
    }

    fun bind(mainActivity: MainView) {
        this.mainActivity = mainActivity
    }

    fun unbind() {
        mainActivity = null
    }
}