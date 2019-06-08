package com.amazonaws.lambda.demo

import com.pietrantuono.entities.Tag

data class ServerTag @JvmOverloads constructor(
    override val title: String,
    override val hidden: Boolean = false,
    override val id: Long
) : Tag