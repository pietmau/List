package com.pppp.database.pokos

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pppp.entities.Category
import com.pppp.entities.CheckList
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class CheckListImpl @JvmOverloads constructor(override val title: String = "",
                         override val categories: List<Category> = emptyList()) : CheckList {

    @get:[Exclude]
    override var id: String? = null
}