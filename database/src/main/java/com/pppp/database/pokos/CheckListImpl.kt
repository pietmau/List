package com.pppp.database.pokos

import com.google.firebase.firestore.Exclude
import com.pppp.entities.Category
import com.pppp.entities.CheckList
import kotlinx.android.parcel.Parcelize

@Parcelize
class CheckListImpl(override val title: String,
                    override val categories: List<Category>) : CheckList {
    @Exclude
    override var id: String? = null
}