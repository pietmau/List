package com.pppp.travelchecklist.selector

class SelectorController {

    var displayedChild: Int = -1
        get() = if (field < 0) 0 else field
}