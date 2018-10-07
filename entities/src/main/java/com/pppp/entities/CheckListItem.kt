package com.pppp.entities

import android.os.Parcelable

interface CheckListItem : Parcelable {
    var title: String
    var checked: Boolean
    var priority: Int
    var description: String?
    var category: Category
    var tags: List<Tag>
    var optional: Boolean
    var id: String?
}

