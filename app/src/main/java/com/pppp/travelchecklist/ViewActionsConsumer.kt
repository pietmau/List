package com.pppp.travelchecklist

interface ViewActionsConsumer<T : ViewAction> {
    fun accept(t: T)
}