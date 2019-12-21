package com.pppp.travelchecklist.repository.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.pppp.entities.pokos.RoomCategoryProxy
import com.pppp.entities.pokos.RoomCheckListItem
import com.pppp.entities.pokos.RoomCheckListItemProxy
import com.pppp.entities.pokos.RoomTag
import com.pppp.entities.pokos.RoomTravelCheckList
import com.pppp.entities.pokos.RoomTravelCheckListProxy

@Dao
interface RoomTravelChecklistRepositoryDao {

    @Query("SELECT * FROM ListId WHERE id=1")
    suspend fun getLastVisitedId(): ListId

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLastVisitedList(listId: ListId)

    @Transaction
    @Query("SELECT * FROM RoomTravelCheckListProxy WHERE id=:listId")
    fun getListById(listId: Long): LiveData<RoomTravelCheckList?>

    @Transaction
    @Query("SELECT * FROM RoomTravelCheckListProxy")
    fun getAllListsAndUpdates(): LiveData<List<RoomTravelCheckList?>>

    @Transaction
    @Query("SELECT * FROM RoomCheckListItemProxy WHERE id=:id")
    suspend fun getChecklistItemById(id: Long): RoomCheckListItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTravelChecklist(travelCheckListProxy: RoomTravelCheckListProxy): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCategory(roomCategoryProxy: RoomCategoryProxy): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCheckListItem(roomCheckListItem: RoomCheckListItemProxy): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTag(roomTag: RoomTag)
}