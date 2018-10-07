package com.pppp.database.pokos

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pppp.entities.Tag
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class TagImpl @JvmOverloads constructor(override var title: String = "",
                          override var hidden: Boolean = false) : Tag {
    @get:[Exclude]
    override var id: String? = null
}