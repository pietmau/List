package com.pppp.travelchecklist.model


data class CheckList(
        val title: String,
        val cards: List<Card>,
        val id: Long)