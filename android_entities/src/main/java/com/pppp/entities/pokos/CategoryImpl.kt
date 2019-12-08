package com.pppp.entities.pokos

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pietrantuono.entities.Category
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryImpl @JvmOverloads constructor(
    @Embedded
    var categoryProxy: CategoryProxy,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    override var items: List<CheckListItemImpl> = emptyList()
    ) : Parcelable, Category by categoryProxy

@Entity
@Parcelize
data class CategoryProxy @JvmOverloads constructor(
    override var title: String,
    override var description: String?,
    @Ignore
    override val items: List<CheckListItemImpl> = emptyList(),
    @PrimaryKey(autoGenerate = true)
    override var id: Long?,
    var checkListId: Long
) : Parcelable, Category
