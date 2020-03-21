package com.pppp.travelchecklist.createlist.model.models

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

inline fun <reified OUT : Any> MutableLiveData<out Any>.filterInstance(): MutableLiveData<OUT> =
    MediatorLiveData<OUT>().apply {
        addSource(this@filterInstance) {
            if (it is OUT) {
                value = it
            }
        }
    }

fun <IN : Any?, OUT : Any?> MutableLiveData<IN>.map(trasform: (IN) -> OUT): MutableLiveData<OUT> =
    MediatorLiveData<OUT>().apply {
        addSource(this@map) {
            value = trasform(it)
        }
    }

fun <T : Any?> MutableLiveData<T>.filter(predicate: (T) -> Boolean): MutableLiveData<T> =
    MediatorLiveData<T>().apply {
        addSource(this@filter) {
            if (predicate(it)) {
                value = it
            }
        }
    }

fun <T : Any> MutableLiveData<T?>.filterNotNUll(): MutableLiveData<T> =
    MediatorLiveData<T>().apply {
        addSource(this@filterNotNUll) {
            if (it != null) {
                value = it
            }
        }
    }