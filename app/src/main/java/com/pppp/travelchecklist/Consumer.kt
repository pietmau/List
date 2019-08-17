package com.pppp.travelchecklist

interface Consumer<T> {
    fun accept(t: T)
}