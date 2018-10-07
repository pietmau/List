package com.pppp.database.pokos

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pppp.entities.Category
import com.pppp.entities.CheckListItem
import com.pppp.entities.Tag
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class CheckListItemImpl @JvmOverloads constructor(override var title: String = "",
                                                       override var checked: Boolean = false,
                                                       override var priority: Int = 5,
                                                       override var description: String? = null,
                                                       override var category: Category = CategoryImpl(),
                                                       override var tags: List<Tag> = emptyList(),
                                                       override var optional: Boolean = false) : CheckListItem {

    @get:[Exclude]
    override var id: String? = null
}