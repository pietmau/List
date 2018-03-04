package com.pppp.travelchecklist.model

class Priority(val value: Int) {
    val MAX = 10
    val MIN = 0

    init {
        if (value < MIN || value > MAX) throw IllegalArgumentException()
    }
}