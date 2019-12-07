package com.pppp.entities.pokos

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.pietrantuono.entities.CheckList
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CheckListImpl(
    @Embedded
    val checkListProxy: CheckListProxy,
    @Relation(
        parentColumn = "id",
        entityColumn = "checkListId"
    )
    override val categories: List<CategoryImpl> = emptyList()
) : Parcelable, CheckList by checkListProxy

@Entity
@Parcelize
data class CheckListProxy @JvmOverloads constructor(
    override val title: String,
    @Ignore
    override val categories: List<CategoryImpl> = emptyList(),
    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0
) : Parcelable, CheckList