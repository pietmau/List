package com.pppp.entities.pokos

import android.os.Parcelable
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.pietrantuono.entities.CheckListItem
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class CheckListItemImpl @JvmOverloads constructor(
    override var title: String = "",
    override var checked: Boolean = false,
    override var priority: Int = 5,
    override var description: String? = null,
    override var tags: List<TagImpl> = emptyList(),
    override var optional: Boolean = false,
    override val categoryId: String = "",
    override var id: String = "",
    val alertTimeInMills: Long? = null,
    val isAlertOn: Boolean = false
) : Parcelable, CheckListItem