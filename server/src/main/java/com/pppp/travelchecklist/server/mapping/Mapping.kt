package com.pppp.travelchecklist.server.mapping

data class Mapping @JvmOverloads constructor(
    var itemId: String = "",
    var tagsIds: List<String> = emptyList()
)