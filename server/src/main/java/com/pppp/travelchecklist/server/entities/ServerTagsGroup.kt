package com.pppp.travelchecklist.server.entities

import com.pietrantuono.entities.Tag
import com.pietrantuono.entities.TagsGroup

data class ServerTagsGroup(
    override val title: String,
    override val description: String? = null,
    override val tags: MutableList<Tag>,
    override val exclusive: Boolean = false,
    override val id: String
) : TagsGroup