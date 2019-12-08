package com.pppp.travelchecklist.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CategoryProxy
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.CheckListProxy
import com.pppp.entities.pokos.TravelCheckListProxy

@Database(entities = arrayOf(CheckListProxy::class, CategoryProxy::class, ListId::class, TravelCheckListProxy::class, CheckListItemImpl::class), version = 1)
abstract class RoomTravelChecklistRepositoryDatabase : RoomDatabase() {
    abstract fun roomTravelChecklistRepositoryDao(): RoomTravelChecklistRepositoryDao
}