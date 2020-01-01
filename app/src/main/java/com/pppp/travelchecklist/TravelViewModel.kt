package com.pppp.travelchecklist

interface TravelViewModel<VIEW_STATE : ViewState, VIEW_INTENT : ViewIntent, TRANSIENT_EVENT : TransientEvent> : ViewStatesProducer<VIEW_STATE>,
    ViewActionsConsumer<VIEW_INTENT>,
    TransientEventsProducer<TRANSIENT_EVENT>