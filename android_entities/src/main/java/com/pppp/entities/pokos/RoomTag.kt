package com.pppp.entities.pokos

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pietrantuono.entities.Tag
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RoomTag @JvmOverloads constructor(
    override var title: String,
    override var hidden: Boolean = false,
    override var id: Long? = null
) : Parcelable, Tag