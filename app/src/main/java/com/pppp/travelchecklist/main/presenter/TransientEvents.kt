package com.pppp.travelchecklist.main.presenter

interface TransientEvents<E> {

    fun subscribe(observer: ((E) -> Unit)? = null)
}
