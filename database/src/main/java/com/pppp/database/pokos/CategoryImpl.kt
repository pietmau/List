package com.pppp.database.pokos

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pppp.entities.Category
import com.pppp.entities.CheckListItem
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class CategoryImpl @JvmOverloads constructor(override var title: String = "",
                                                  override var description: String? = null,
                                                  override var items: List<CheckListItem> = emptyList()) : Category {

    @get:[Exclude]
    override var id: String? = null
}