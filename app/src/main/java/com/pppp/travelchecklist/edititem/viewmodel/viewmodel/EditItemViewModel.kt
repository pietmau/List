package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer

class EditItemViewModel :
    ViewStatesProducer<EditItemViewState>,
    TransientEventsProducer<EditItemTransientEvent>,
    ViewActionsConsumer<EditItemViewIntent>,
    ViewModel() {

    private val statesInternal: MutableLiveData<EditItemViewState> by lazy { MutableLiveData<EditItemViewState>() }

    override val transientEvents: LiveData<EditItemTransientEvent>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val states: LiveData<EditItemViewState>
        get() = statesInternal

    override fun accept(t: EditItemViewIntent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

