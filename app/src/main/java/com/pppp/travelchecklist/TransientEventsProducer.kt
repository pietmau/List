package com.pppp.travelchecklist

import androidx.lifecycle.LiveData

interface TransientEventsProducer<E : TransientEvent> {

    val transientEvents: LiveData<E>
}
