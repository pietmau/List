package com.pppp.travelchecklist.repository

class ListNotFoundException(userId: String?, listId: String?) : Exception("List $listId not found for user $userId") {}