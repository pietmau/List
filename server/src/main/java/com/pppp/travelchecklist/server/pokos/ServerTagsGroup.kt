package com.pppp.travelchecklist.server.pokos

import com.pietrantuono.entities.Tag
import com.pietrantuono.entities.TagsGroup

data class ServerTagsGroup(
    override val title: String,
    override val description: String?,
    override val tags: MutableList<Tag>,
    override val exclusive: Boolean,
    override val id: Long
) : TagsGroup