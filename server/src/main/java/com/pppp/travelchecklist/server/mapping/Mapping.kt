package com.pppp.travelchecklist.server.mapping

data class Mapping @JvmOverloads constructor(
    var itemId: Long = -1,
    var tagsIds: List<Long> = emptyList()
)