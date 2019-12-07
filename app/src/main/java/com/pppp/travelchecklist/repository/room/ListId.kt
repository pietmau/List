package com.pppp.travelchecklist.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListId(val value: Long, @PrimaryKey val id: Long = 1)