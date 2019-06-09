package com.pppp.travelchecklist.main.presenter

class MainPresenter {
    private var mainActivity: MainView? = null

    fun bind(mainActivity: MainView) {
        this.mainActivity = mainActivity
    }

    fun unbind() {
        mainActivity = null
    }
}