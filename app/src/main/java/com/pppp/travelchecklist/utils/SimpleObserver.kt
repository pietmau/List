package com.pppp.travelchecklist.utils

import io.reactivex.observers.DisposableObserver


open class SimpleObserver<T>: DisposableObserver<T>() {
    override fun onError(e: Throwable) {

    }

    override fun onNext(t: T) {

    }

    override fun onComplete() {

    }

}