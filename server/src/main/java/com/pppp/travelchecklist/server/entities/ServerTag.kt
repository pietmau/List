package com.amazonaws.lambda.demo

import com.pietrantuono.entities.Tag

data class ServerTag @JvmOverloads constructor(
    override var title: String = "",
    override var hidden: Boolean = false,
    override var id: String = ""
) : Tag