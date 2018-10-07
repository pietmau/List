package com.pppp.database.pokos

import com.google.firebase.firestore.Exclude
import com.pppp.entities.Tag
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TagImpl(override var title: String, override var hidden: Boolean) : Tag {
    @Exclude
    override var id: String? = null
}