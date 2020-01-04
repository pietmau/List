package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.travelchecklist.TransientLiveData
import com.pppp.travelchecklist.TravelViewModel
import com.pppp.travelchecklist.edititem.model.EditItemModel
import com.pppp.travelchecklist.main.model.Mapper
import com.pppp.travelchecklist.utils.exhaustive
import  com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemTransientEvent.SelectDate
import  com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemTransientEvent.SelectTime

class EditItemTravelViewModel(
    private val listId: String,
    private val categoryId: String,
    private val itemId: String,
    private val model: EditItemModel,
    private val mapper: Mapper<CheckListItem, EditItemViewState>,
    private val dateAndTimeProvider: DateAndTimeProvider
) :
    TravelViewModel<EditItemViewState, EditItemViewIntent, EditItemTransientEvent>,
    ViewModel() {
    override val transientEvents: LiveData<EditItemTransientEvent>
        get() = transientEventsInternal

    private lateinit var item: CheckListItem

    private val transientEventsInternal: MutableLiveData<EditItemTransientEvent> by lazy { TransientLiveData<EditItemTransientEvent>() }

    private val statesInternal: MutableLiveData<EditItemViewState> by lazy {
        MutableLiveData<EditItemViewState>().also {
            model.retrieveItem(listId, categoryId, itemId, {}, { item ->
                emitItem(item)
            })
        }
    }

    private fun emitItem(item: CheckListItem) {
        this@EditItemTravelViewModel.item = item
        statesInternal.postValue(mapper.map(item))
    }

    override val states: LiveData<EditItemViewState>
        get() = statesInternal

    override fun accept(intent: EditItemViewIntent) {
        when (intent) {
            is EditItemViewIntent.OnAlertActivated -> onAlertActivated(intent.activated)
            is EditItemViewIntent.DateSet -> onDateSet(intent.year, intent.monthOfYear, intent.dayOfMonth)
            EditItemViewIntent.OnDateClicked -> transientEventsInternal.postValue(SelectDate(requireNotNull(item.alertTimeInMills)))
            EditItemViewIntent.OnTimeClicked -> transientEventsInternal.postValue(SelectTime(requireNotNull(item.alertTimeInMills)))
            is EditItemViewIntent.OnTimeSet -> onTimeSet(intent.hourOfDay, intent.minute)
            is EditItemViewIntent.OnDataChanged -> onDataChanged(intent.title, intent.description, intent.priority)
            EditItemViewIntent.OnSaveClicked -> onSaveClicked()
        }.exhaustive
    }

    private fun onSaveClicked() = model.updateItem(listId, categoryId, itemId, item as CheckListItemImpl)

    private fun onDataChanged(title: String?, description: String?, priority: Int?) {
        val title = title ?: item.title
        val description = description ?: item.description
        val item = (item as CheckListItemImpl).copy(title = title, description = description, priority = priority ?: item.priority)
        emitItem(item)
    }

    private fun onTimeSet(hourOfDay: Int, minute: Int) = onDateOrTimeSet(dateAndTimeProvider.onTimeSet(item.alertTimeInMills, hourOfDay, minute))

    private fun onDateSet(year: Int, monthOfYear: Int, dayOfMonth: Int) = onDateOrTimeSet(
        dateAndTimeProvider.onDateSet(
            item.alertTimeInMills,
            year,
            monthOfYear,
            dayOfMonth
        )
    )

    private fun onDateOrTimeSet(alertTimeInMills: Long) {
        val item = (item as CheckListItemImpl).copy(alertTimeInMills = alertTimeInMills)
        emitItem(item)
    }

    private fun onAlertActivated(activated: Boolean) {
        val alertTimeInMills = dateAndTimeProvider.getDefaultAlertTime(item.alertTimeInMills)
        val item = (item as CheckListItemImpl).copy(isAlertOn = activated, alertTimeInMills = alertTimeInMills)
        emitItem(item)
    }

}

