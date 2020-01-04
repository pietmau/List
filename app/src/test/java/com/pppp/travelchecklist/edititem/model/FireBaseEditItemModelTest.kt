package com.pppp.travelchecklist.edititem.model

import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.CheckListItem
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.travelchecklist.list.model.getList
import com.pppp.travelchecklist.list.model.userId
import io.mockk.CapturingSlot
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.Before
import io.mockk.slot
import io.mockk.verify
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

private val LIST = "list"
private val CAT = "cat"
private val ITEM = "item"
private val USER_ID = "user"

class FireBaseEditItemModelTest {
    private val task3: Task<Continuation<DocumentSnapshot, Task<Void>?>> = mockk {
        every { addOnFailureListener(any()) } returns mockk()
    }
    private val task2: Task<Continuation<DocumentSnapshot, Task<Void>?>> = mockk(name = "Second") {
        every { addOnSuccessListener(any()) } returns task3
    }
    private val documentSnapshot: DocumentSnapshot = mockk {
        every { toObject<Any?>(any()) } returns TravelCheckListImpl(
            categories = listOf(
                CategoryImpl(
                    id = CAT,
                    items = listOf(CheckListItemImpl(id = ITEM))
                )
            )
        )
    }
    private val capturingSlot: CapturingSlot<Continuation<DocumentSnapshot, Task<Void>?>> = slot()
    private val task1: Task<DocumentSnapshot> = mockk(name = "First") {
        @Suppress("UNCHECKED_CAST")
        every { continueWith<Any?>(any()) } returns task2 as Task<Any?>
        every { result } returns documentSnapshot
    }
    private val documentReference: DocumentReference = mockk(relaxed = true) {
        every { get() } returns task1

    }
    private val auth: FirebaseAuth = mockk {
        every { userId } returns USER_ID
    }
    private val db: FirebaseFirestore = mockk {
        every { getList(USER_ID, LIST) } returns documentReference
    }
    private val model = FireBaseEditItemModel(auth, db, LIST, CAT, ITEM)

    @Test
    fun `When retrieve item make right calls`() {
        // When
        model.retrieveItem()

        // Then
        verify { task1.continueWith(capture(capturingSlot)) }
        capturingSlot.captured.then(task1)
        verify { task2.addOnSuccessListener(any()) }
        verify { task3.addOnFailureListener(any()) }
    }

    @Test
    fun `When success returns success`() {
        // When
        val onSuccess: (CheckListItem) -> Unit = mockk(relaxUnitFun = true)
        every { onSuccess(any()) } just Runs
        model.retrieveItem({}, onSuccess)

        // Then
        val slot: CapturingSlot<OnSuccessListener<Any>> = slot()
        verify { task2.addOnSuccessListener(capture(slot)) }
        val item = mockk<CheckListItemImpl>()
        slot.captured.onSuccess(item)
        verify { onSuccess.invoke(item) }
    }

    @Test
    fun `When updates returns updates`() {
        // When
        model.updateItem(mockk(relaxed = true))

        // Then
        val slot: CapturingSlot<Continuation<DocumentSnapshot, Task<Void>?>> = slot()
        verify { task1.continueWith(capture(slot)) }
        slot.captured.then(task1)
        verify { documentReference.update(CATEGORIES, any()) }
    }

}