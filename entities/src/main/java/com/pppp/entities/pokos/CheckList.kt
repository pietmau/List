package com.pppp.entities.pokos

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class CheckList @JvmOverloads constructor(
    val title: String = "",
    val categories: List<Category> = emptyList()) : Parcelable {
    @get:[Exclude]
    var id: String? = null
}