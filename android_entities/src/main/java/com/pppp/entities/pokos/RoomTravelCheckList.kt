package com.pppp.entities.pokos

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.pietrantuono.entities.TravelCheckList
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RoomTravelCheckList @JvmOverloads constructor(
    @Embedded
    val travelCheckListProxy: RoomTravelCheckListProxy,
    @Relation(
        parentColumn = "id",
        entityColumn = "checkListId",
        entity = RoomCategoryProxy::class
    )
    override var categories: List<RoomCategory> = emptyList()
) : TravelCheckList by travelCheckListProxy, Parcelable

