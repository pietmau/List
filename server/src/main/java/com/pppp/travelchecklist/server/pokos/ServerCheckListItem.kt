package com.pppp.travelchecklist.server.pokos

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag

data class ServerCheckListItem(
    override var title: String,
    override var checked: Boolean,
    override var priority: Int,
    override var description: String?,
    override val category: Category?,
    override val tags: List<Tag>,
    override var optional: Boolean,
    override var id: String?
) : CheckListItem {
}