package com.pppp.travelchecklist

interface ViewActionsConsumer<T : ViewIntent> {
    fun accept(t: T)
}