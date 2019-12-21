package com.pppp.entities.pokos

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.pietrantuono.entities.CheckListItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RoomCheckListItem @JvmOverloads constructor(
    @Embedded
    var roomCheckListItemProxy: RoomCheckListItemProxy,
    @Relation(
        parentColumn = "id",
        entityColumn = "itemId",
        entity = RoomTag::class
    )
    override var tags: List<RoomTag> = emptyList()
) : Parcelable, CheckListItem by roomCheckListItemProxy

