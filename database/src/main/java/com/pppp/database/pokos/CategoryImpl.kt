package com.pppp.database.pokos

import com.google.firebase.firestore.Exclude
import com.pppp.entities.Category
import com.pppp.entities.CheckListItem
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoryImpl(override var title: String,
                   override var description: String?,
                   override var items: List<CheckListItem>) : Category {
    @Exclude
    override var id: String? = null
}