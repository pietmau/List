package com.pppp.travelchecklist

import androidx.lifecycle.LiveData

interface ViewStatesProducer<T : ViewState> {
    val states: LiveData<T>
}