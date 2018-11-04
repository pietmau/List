package com.pppp.entities.pokos

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pietrantuono.entities.CheckList
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class CheckListImpl @JvmOverloads constructor(
    override val title: String = "",
    override val categories: List<CategoryImpl> = emptyList()) : Parcelable, CheckList {
    @get:[Exclude]
    override var id: String? = null
}