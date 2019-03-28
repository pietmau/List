package com.pppp.travelchecklist.server.categories

import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem

data class ServerCategory(
    override val title: String,
    override val description: String? = null,
    override val items: List<CheckListItem> = emptyList(),
    override var id: String
) : Category