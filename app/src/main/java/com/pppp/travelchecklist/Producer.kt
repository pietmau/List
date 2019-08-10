package com.pppp.travelchecklist

import androidx.lifecycle.LiveData

interface Producer<T> {
    val states: LiveData<T>
}