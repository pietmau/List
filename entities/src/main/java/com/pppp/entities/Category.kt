package com.pppp.entities

import android.os.Parcelable

interface Category : Parcelable {
    var title: String
    var description: String?
    var items: List<CheckListItem>
    var id: String?
}