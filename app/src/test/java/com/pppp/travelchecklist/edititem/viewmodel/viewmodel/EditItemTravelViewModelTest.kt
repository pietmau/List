package com.pppp.travelchecklist.edititem.viewmodel.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.isNull
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.travelchecklist.edititem.model.EditItemModel
import org.junit.Before
import org.junit.Test
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.OnAlertActivated
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.DateSet
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemViewIntent.OnDateClicked
import com.pppp.travelchecklist.edititem.viewmodel.viewmodel.EditItemTransientEvent.SelectDate
import org.assertj.core.api.Assertions.assertThat

private const val NOW: Long = 11121314
private const val SOME_TIME: Long = 123456
private const val SOME_OTHER_TIME: Long = 78910
private const val ANOTHER_TIME: Long = 999999
private const val YEAR = 1
private const val MONTH = 12
private const val DAY = 30

internal class EditItemTravelViewModelTest {
    private val ID = "id"
    private val viewState: EditItemViewState = mock()
    private val item: CheckListItem = CheckListItemImpl(alertTimeInMills = NOW, id = ID)
    private val model: EditItemModel = mock()
    private val mapper: EditItemMapper = mock {
        on { map(isA()) } doReturn viewState
        on { getDefaultAlertTime(SOME_TIME) } doReturn SOME_OTHER_TIME
        on { onDateSet(NOW, YEAR, MONTH, DAY) } doReturn ANOTHER_TIME
    }
    private val transientEventsInternal: MutableLiveData<EditItemTransientEvent> = mock()
    private val statesInternal: MutableLiveData<EditItemViewState> = mock()
    private lateinit var editItemTravelViewModel: EditItemTravelViewModel
    private val functionCaptor = argumentCaptor<((CheckListItem) -> Unit)>()
    private val checkListItemCaptor = argumentCaptor<CheckListItem>()
    private val viewStateCaptor = argumentCaptor<EditItemViewState>()
    private val editItemTransientEventCaptor = argumentCaptor<EditItemTransientEvent>()

    @Before
    fun setUp() {
        editItemTravelViewModel = EditItemTravelViewModel(model, mapper, transientEventsInternal, statesInternal)
        verify(model).retrieveItem(isNull(), functionCaptor.capture())
        functionCaptor.lastValue(item)
        verify(mapper, times(1)).map(checkListItemCaptor.capture())
        assertThat(checkListItemCaptor.firstValue.alertTimeInMills).isEqualTo(NOW)
        assertThat(checkListItemCaptor.firstValue.isAlertOn).isFalse()
        verify(statesInternal, times(1)).postValue(viewStateCaptor.capture())
        assertThat(viewStateCaptor.firstValue).isEqualTo(viewState)
    }

    @Test
    internal fun `when starts gets item`() {
        verify(model).retrieveItem(isNull(), isA())
    }

    @Test
    internal fun `when starts maps item`() {
        verify(mapper).map(item)
    }

    @Test
    internal fun `when starts emits item`() {
        verify(statesInternal).postValue(viewState)
    }

    @Test
    internal fun `when alert activated then emits activated`() {
        // When
        editItemTravelViewModel.accept(OnAlertActivated(true))

        // Then
        verify(mapper, times(2)).map(checkListItemCaptor.capture())
        assertThat(checkListItemCaptor.lastValue.isAlertOn).isTrue()
    }

    @Test
    internal fun `when sets date then emits new date`() {
        // When
        editItemTravelViewModel.accept(DateSet(year = YEAR, monthOfYear = MONTH, dayOfMonth = DAY))

        // Then
        verify(mapper, times(2)).map(checkListItemCaptor.capture())
        assertThat(checkListItemCaptor.lastValue.alertTimeInMills).isEqualTo(ANOTHER_TIME)
        verify(statesInternal, times(2)).postValue(viewState)
    }

    @Test
    internal fun `when date clicked then emits select date`() {
        // When
        editItemTravelViewModel.accept(OnDateClicked)

        // Then
        verify(transientEventsInternal).postValue(editItemTransientEventCaptor.capture())
        assertThat((editItemTransientEventCaptor.firstValue as SelectDate).timeInMills).isEqualTo(NOW)
    }
}