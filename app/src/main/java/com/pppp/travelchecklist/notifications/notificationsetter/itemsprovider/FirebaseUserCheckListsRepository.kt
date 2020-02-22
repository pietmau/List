package com.pppp.travelchecklist.notifications.notificationsetter.itemsprovider

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pietrantuono.entities.TravelCheckList

class FirebaseUserCheckListsRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : UserCheckListsRepository {

    override fun getUserCheckLists(): List<TravelCheckList> {
        TODO()
    }
}