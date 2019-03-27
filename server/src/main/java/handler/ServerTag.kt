package com.amazonaws.lambda.demo

import com.pietrantuono.entities.Tag

data class ServerTag(
    override var title: String,
    override var hidden: Boolean = false,
    override var id: String? = null
) : Tag