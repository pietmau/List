package com.pppp.travelchecklist

interface Consumer<T> {
    fun push(t: T)
}