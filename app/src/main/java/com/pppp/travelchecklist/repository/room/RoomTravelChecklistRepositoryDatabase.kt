package com.pppp.travelchecklist.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.CheckListProxy

@Database(entities = arrayOf(CheckListProxy::class, CategoryImpl::class), version = 1)
abstract class RoomTravelChecklistRepositoryDatabase : RoomDatabase() {
    abstract fun roomTravelChecklistRepositoryDao(): RoomTravelChecklistRepositoryDao
}