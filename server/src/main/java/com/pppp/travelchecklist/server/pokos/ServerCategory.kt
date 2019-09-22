package com.pppp.travelchecklist.server.categories

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem

data class ServerCategory(
    override val title: String,
    override val description: String? = null,
    override val items: MutableList<CheckListItem> = mutableListOf(),
    override var id: String
) : Category