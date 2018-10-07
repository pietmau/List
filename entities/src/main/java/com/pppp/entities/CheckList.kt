package com.pppp.entities

import android.os.Parcelable

interface CheckList : Parcelable {
    val title: String
    val categories: List<Category>
    var id: String?
}