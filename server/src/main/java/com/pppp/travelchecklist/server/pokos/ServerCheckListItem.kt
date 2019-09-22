package com.pppp.travelchecklist.server.pokos

import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag

data class ServerCheckListItem(
    override var title: String,
    override var checked: Boolean = false,
    override var priority: Int = 6,
    override var description: String? = null,
    override val categoryId: String,
    override val tags: List<Tag> = emptyList(),
    override var optional: Boolean = false,
    override var id: String
) : CheckListItem