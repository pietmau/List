package com.pppp.travelchecklist.model

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


open class SimpleObserver<T>:Observer<T> {
    override fun onError(e: Throwable) {

    }

    override fun onNext(t: T) {

    }

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }
}