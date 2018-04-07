package com.pppp.travelchecklist.selector.view

class SelectorController {
    var whoIsTravelling: MutableList<WhoIsTravellingView.WhoIsTravelling> = mutableListOf()

    var displayedChild: Int = -1
        get() = if (field < 0) 0 else field

    fun setWhoIsTravellling(whoIsTravelling: List<WhoIsTravellingView.WhoIsTravelling>?) {
        this.whoIsTravelling.clear()
        whoIsTravelling?.let { this.whoIsTravelling.addAll(it) }
    }
}