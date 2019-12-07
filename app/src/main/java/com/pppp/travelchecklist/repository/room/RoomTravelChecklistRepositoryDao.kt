package com.pppp.travelchecklist.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.CheckListProxy

@Dao
interface RoomTravelChecklistRepositoryDao {

    @Insert
    fun insertCheckListImpl(proxy: CheckListProxy): Long

    @Insert
    fun insertCategories(list: List<CategoryImpl>)
}