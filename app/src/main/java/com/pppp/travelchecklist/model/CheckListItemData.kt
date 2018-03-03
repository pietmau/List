package com.pppp.travelchecklist.model


data class CheckListItemData(
        var title: String,
        var checked: Boolean = false,
        var priority: Priority = Priority(5)
) {

}

class Priority(val value: Int) {
    val MAX = 10
    val MIN = 0

    init {
        if (value < MIN || value > MAX) throw IllegalArgumentException()
    }
}
