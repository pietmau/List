package com.pppp.entities

import android.os.Parcelable

interface TagsGroup : Parcelable {
    var title: String
    var description: String?
    var tags: List<Tag>
    var exclusive: Boolean
    var id: String?
}