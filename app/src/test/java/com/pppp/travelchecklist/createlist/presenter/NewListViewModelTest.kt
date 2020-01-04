package com.pppp.travelchecklist.createlist.presenter

import androidx.lifecycle.MutableLiveData
import com.pppp.travelchecklist.utils.ResourcesWrapper
import io.mockk.CapturingSlot
import io.mockk.Runs
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

private const val ID = "id"

class NewListViewModelTest {
    private val transientState: CapturingSlot<NewListViewModel.TransientState> = slot()
    private val captor: CapturingSlot<NewListViewModel.NewListViewState> = slot()
    private val transientStates: (NewListViewModel.TransientState) -> Unit = mockk {
        every { this@mockk.invoke(any()) } just Runs
    }
    private val liveData: MutableLiveData<NewListViewModel.NewListViewState> = mockk(relaxed = true)
    private val resourcesWrapper: ResourcesWrapper = mockk()
    private val model: Model = mockk(relaxed = true) {
        every { generate() } returns Single.just(ID)
    }
    private val viewModel = NewListViewModel(model, resourcesWrapper, liveData, transientStates)

    @Test
    internal fun `data is complete updates model and updates UI`() {
        // Given
        every { model.isDataComplete() } returns true

        // When
        viewModel.onFinishClicked()

        // Then
        verify { model.checkListId = ID }
        verify { liveData.postValue(capture(captor)) }
        assertThat(captor.captured.showProgress).isFalse()
        assertThat(captor.captured.listId).isEqualTo(ID)
        assertThat(captor.captured.enableFinish).isTrue()
    }

    @Test
    internal fun `data is not complete emits error`() {
        // Given
        every { model.isDataComplete() } returns false

        // When
        viewModel.onFinishClicked()

        // Then
        //verify { transientStates(capture(transientState)) }
    }
}