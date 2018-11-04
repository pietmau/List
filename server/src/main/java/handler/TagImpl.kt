package com.amazonaws.lambda.demo

import com.pietrantuono.entities.Tag

class TagImpl : Tag {
    override var title: String = ""
    override var hidden: Boolean = false
    override var id: String? = null
}