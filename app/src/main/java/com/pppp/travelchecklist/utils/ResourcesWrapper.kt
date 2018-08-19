package com.pppp.travelchecklist.utils

import android.content.res.Resources
import android.support.annotation.StringRes

class ResourcesWrapper(private val resources: Resources) {

    fun getString(@StringRes string: Int): String? = resources.getString(string)
}