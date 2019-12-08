package com.pppp.travelchecklist.repository.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.CheckListProxy

@Dao
interface RoomTravelChecklistRepositoryDao {

    @Insert
    suspend fun insertCheckListImpl(proxy: CheckListProxy): Long

    @Query("SELECT * FROM ListId WHERE id=1")
    suspend fun getLastVisitedId(): ListId

    @Insert
    suspend fun insertCategories(list: List<CategoryImpl>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLastVisitedList(listId: ListId)

    @Transaction
    @Query("SELECT * FROM CheckListProxy WHERE id=:listId")
    fun getListById(listId: Long): LiveData<CheckListImpl>

    @Transaction
    @Query("SELECT * FROM CheckListProxy")
    fun geAllLists(): List<CheckListImpl>

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun ffff(it: List<CategoryImpl>)
}