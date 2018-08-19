package com.pppp.travelchecklist.database

import io.reactivex.observers.DisposableObserver


interface DestinationPresenter {

    fun unsubscribe()

    fun getCountries(observer: DisposableObserver<List<Country>>)
}