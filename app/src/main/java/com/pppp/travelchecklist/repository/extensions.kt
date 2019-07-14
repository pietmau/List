package com.pppp.travelchecklist.repository

import com.google.firebase.firestore.FirebaseFirestore

private val USERS = "users"
private val USERS_CHECKLISTS = "user_checklists"

fun FirebaseFirestore.getAllUserCheckLists(userId: String) = this.collection(USERS)
    .document(userId)
    .collection(USERS_CHECKLISTS)

fun FirebaseFirestore.getCheckListsById(userId: String, listId: String) = this.getAllUserCheckLists(userId).document(listId)