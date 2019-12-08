package com.pppp.travelchecklist.repository.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.pietrantuono.entities.TravelCheckList
import com.pppp.entities.pokos.CategoryImpl
import com.pppp.entities.pokos.CategoryProxy
import com.pppp.entities.pokos.CheckListImpl
import com.pppp.entities.pokos.CheckListItemImpl
import com.pppp.entities.pokos.CheckListProxy
import com.pppp.entities.pokos.TravelCheckListImpl
import com.pppp.entities.pokos.TravelCheckListProxy

@Dao
interface RoomTravelChecklistRepositoryDao {

    @Insert
    suspend fun insertCheckListImpl(proxy: CheckListProxy): Long

    @Query("SELECT * FROM ListId WHERE id=1")
    suspend fun getLastVisitedId(): ListId

    @Insert
    fun insertCategories(list: List<CategoryProxy>)

    @Insert
    fun insertItems(list: List<CheckListItemImpl>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLastVisitedList(listId: ListId)

    @Transaction
    @Query("SELECT * FROM TravelCheckListProxy WHERE id=:listId")
    fun getListById(listId: Long): LiveData<TravelCheckListImpl?>

    @Transaction
    @Query("SELECT * FROM CheckListProxy")
    fun geAllLists(): List<CheckListImpl>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTravelChecklist(travelCheckListProxy: TravelCheckListProxy): Long
}