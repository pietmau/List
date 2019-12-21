package com.pppp.travelchecklist.repository.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pppp.entities.pokos.RoomCategoryProxy
import com.pppp.entities.pokos.RoomCheckListItem
import com.pppp.entities.pokos.RoomCheckListItemProxy
import com.pppp.entities.pokos.RoomTag
import com.pppp.entities.pokos.RoomTravelCheckListProxy
import com.pppp.entities.pokos.StringListConverter

@Database(entities = arrayOf(RoomCategoryProxy::class, ListId::class, RoomTravelCheckListProxy::class, RoomCheckListItemProxy::class, RoomTag::class), version = 1)
@TypeConverters(StringListConverter::class)
abstract class RoomTravelChecklistRepositoryDatabase : RoomDatabase() {
    abstract fun roomTravelChecklistRepositoryDao(): RoomTravelChecklistRepositoryDao
}