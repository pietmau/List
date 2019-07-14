package com.pppp.travelchecklist.login

interface Consumer<T> {
    fun push(t: T)
}