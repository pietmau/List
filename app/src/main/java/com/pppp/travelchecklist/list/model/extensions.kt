package com.pppp.travelchecklist.list.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.pppp.travelchecklist.repository.USERS
import com.pppp.travelchecklist.repository.USERS_CHECKLISTS
import com.pppp.travelchecklist.repository.UserNotLoggedInException

fun FirebaseFirestore.getList(userId: String, listId: String): DocumentReference = collection(USERS)
    .document(userId)
    .collection(USERS_CHECKLISTS)
    .document(listId)

val FirebaseAuth.userId
    get() = uid ?: throw UserNotLoggedInException()

fun FirebaseFirestore.getUserLists(userId: String): CollectionReference = collection(USERS)
    .document(userId)
    .collection(USERS_CHECKLISTS)
