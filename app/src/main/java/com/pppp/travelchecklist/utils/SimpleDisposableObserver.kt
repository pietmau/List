package com.pppp.travelchecklist.utils

import io.reactivex.observers.DisposableObserver

open class SimpleDisposableObserver<T>: DisposableObserver<T>() {
    override fun onComplete() {

    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {

    }
}