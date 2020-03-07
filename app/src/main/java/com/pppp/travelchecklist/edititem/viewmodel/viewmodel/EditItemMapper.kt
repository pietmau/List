package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

import com.pietrantuono.entities.CheckListItem
import com.pppp.travelchecklist.main.model.Mapper

class EditItemMapper(
    private val formatter: DateAndTimeFormatter,
    private val dateAndTimeProvider: DateAndTimeProvider
) : Mapper<CheckListItem, EditItemViewState> {

    override fun map(checkListItem: CheckListItem): EditItemViewState {
        val title = checkListItem.title
        val description = checkListItem.description
        val priority = checkListItem.priority.toFloat()
        val date = formatter.getDate(checkListItem.alertTimeInMills)
        val time: String = formatter.getTime(checkListItem.alertTimeInMills)
        val isAlertOn: Boolean = checkListItem.isAlertOn
        return EditItemViewState(title, description, priority, date, time, isAlertOn)
    }

    fun onTimeSet(alertTimeInMills: Long?, hourOfDay: Int, minute: Int) =
        dateAndTimeProvider.setTime(alertTimeInMills, hourOfDay, minute)

    fun onDateSet(alertTimeInMills: Long?, year: Int, monthOfYear: Int, dayOfMonth: Int) =
        dateAndTimeProvider.setDate(alertTimeInMills, year, monthOfYear, dayOfMonth)

    fun getDefaultAlertTime(alertTimeInMills: Long?) = dateAndTimeProvider.getDefaultAlertTime(alertTimeInMills)
}