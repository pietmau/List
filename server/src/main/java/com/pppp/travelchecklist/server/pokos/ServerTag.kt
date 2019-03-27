package com.amazonaws.lambda.demo

import com.pietrantuono.entities.Tag

data class ServerTag(
    override val title: String,
    override val hidden: Boolean = false,
    override val id: String
) : Tag