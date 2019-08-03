package com.pppp.travelchecklist.repository

import com.google.firebase.firestore.FirebaseFirestore

internal val USERS = "users"
    internal val USERS_CHECKLISTS = "user_checklists"

fun FirebaseFirestore.getAllUserCheckLists(userId: String) = this.collection(USERS)
    .document(userId)
    .collection(USERS_CHECKLISTS)

fun FirebaseFirestore.getCheckListsById(userId: String, listId: String) = this.getAllUserCheckLists(userId).document(listId)