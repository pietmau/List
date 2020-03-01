package com.pppp.travelchecklist.notifications.alarmsrepository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.TravelCheckList
import com.pppp.travelchecklist.list.model.getUser
import com.pppp.travelchecklist.list.model.userId
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.FirebaseUserCheckListsRepository
import com.pppp.travelchecklist.notifications.alarmsetter.itemsprovider.UserCheckListsRepository
import com.pppp.travelchecklist.utils.TimeProvider
import com.pppp.travelchecklist.utils.TimeProviderImpl
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

const val ALARMS = "alarms"

class FirebaseAlarmsRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val timeProvider: TimeProvider = TimeProviderImpl,
    private val userCheckListsRepository: UserCheckListsRepository = FirebaseUserCheckListsRepository()
) : AlarmsRepository {

    override suspend fun deleteAllAlarms() {
        return suspendCoroutine { continuation ->
            db.getUser(auth.userId)
                .collection(ALARMS)
                .get()
                .addOnSuccessListener { snapshot ->
                    snapshot.documents.forEach { it.reference.delete() }
                    continuation.resume(Unit)
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun getAllAlarms(): List<Alarm> {
        return suspendCoroutine { continuation ->
            db.getUser(auth.userId)
                .collection(ALARMS)
                .get().addOnSuccessListener { query ->
                    val value = query.documents.map { document ->
                        document.toObject(Alarm::class.java)
                    }.filterNotNull()
                    continuation.resume(value)
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun saveAlarms(alarms: List<Alarm>) {
        val collection = db.getUser(auth.userId)
            .collection(ALARMS)
        alarms.forEach { collection.add(it) }
    }

    override suspend fun getValidAlarmsFromItems(): List<Alarm> {
        val now = timeProvider.getCurrentTimeInMills()
        return userCheckListsRepository
            .getUserCheckLists()
            .flatMap { it.getValidAlarms(now) }
    }

}
