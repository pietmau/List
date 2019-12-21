package com.pppp.travelchecklist.api

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag

data class NetworkCategory(
    override val title: String,
    override val description: String?,
    override val items: List<NetworkCheckListItem>,
    override val id: Long?
) : Category

data class NetworkCheckListItem(
    override var title: String,
    override var checked: Boolean,
    override var priority: Int,
    override var description: String?,
    override val categoryId: Long?,
    override val tags: List<NetworkTag>,
    override var optional: Boolean,
    override var id: Long? = null
) : CheckListItem

data class NetworkTag(override val title: String, override val hidden: Boolean = false, override val id: Long? = null, override var itemId: Long?) : Tag