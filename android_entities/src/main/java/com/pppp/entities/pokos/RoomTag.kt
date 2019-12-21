package com.pppp.entities.pokos

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pietrantuono.entities.Tag
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class RoomTag @JvmOverloads constructor(
    override var title: String,
    override var hidden: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    override var id: Long? = null,
    override var itemId: Long? = null
) : Parcelable, Tag