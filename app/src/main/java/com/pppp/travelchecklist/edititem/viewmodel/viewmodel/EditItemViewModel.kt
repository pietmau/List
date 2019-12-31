package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.TransientEventsProducer
import com.pppp.travelchecklist.ViewActionsConsumer
import com.pppp.travelchecklist.ViewStatesProducer
import com.pppp.travelchecklist.edititem.model.EditItemModel
import com.pppp.travelchecklist.main.model.Mapper

class EditItemViewModel(
    private val listId: String,
    private val categoryId: String,
    private val itemId: String,
    private val model: EditItemModel,
    private val mapper: Mapper<CheckListItem, EditItemViewState>
) :
    ViewStatesProducer<EditItemViewState>,
    TransientEventsProducer<EditItemTransientEvent>,
    ViewActionsConsumer<EditItemViewIntent>,
    ViewModel() {

    private val statesInternal: MutableLiveData<EditItemViewState> by lazy {
        MutableLiveData<EditItemViewState>().also {
            model.retrieveItemAndUpdates(listId, categoryId, itemId, {}, { item ->
                // statesInternal.postValue(EditItemViewState())
            })
        }
    }

    override val transientEvents: LiveData<EditItemTransientEvent>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val states: LiveData<EditItemViewState>
        get() = statesInternal

    override fun accept(t: EditItemViewIntent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

