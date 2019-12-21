package com.pppp.entities.pokos

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.pietrantuono.entities.Category
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RoomCategory @JvmOverloads constructor(
    @Embedded
    var categoryProxy: RoomCategoryProxy,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId",
        entity = RoomCheckListItemProxy::class
    )
    override var items: List<RoomCheckListItem> = emptyList()
) : Parcelable, Category by categoryProxy

