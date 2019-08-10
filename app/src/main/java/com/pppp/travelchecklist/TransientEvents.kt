package com.pppp.travelchecklist

interface TransientEvents<E> {

    fun subscribe(observer: ((E) -> Unit)? = null)
}
