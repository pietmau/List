package com.pppp.travelchecklist.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pppp.entities.pokos.RoomCategoryProxy
import com.pppp.entities.pokos.RoomCheckListItem
import com.pppp.entities.pokos.RoomTravelCheckListProxy

@Database(entities = arrayOf(RoomCategoryProxy::class, ListId::class, RoomTravelCheckListProxy::class, RoomCheckListItem::class), version = 1)
abstract class RoomTravelChecklistRepositoryDatabase : RoomDatabase() {
    abstract fun roomTravelChecklistRepositoryDao(): RoomTravelChecklistRepositoryDao
}