package com.pppp.travelchecklist.login

import androidx.lifecycle.LiveData

interface Producer<T> {
    val states: LiveData<T>
}