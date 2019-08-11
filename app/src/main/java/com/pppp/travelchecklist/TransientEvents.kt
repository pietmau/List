package com.pppp.travelchecklist

import androidx.lifecycle.LiveData
import com.pppp.travelchecklist.main.viewmodel.MainViewModel

interface TransientEvents<E> {

    val transientEvents: LiveData<MainViewModel.TransientEvent>
}
