internal const val getTagsWithGroup = "SELECT  travelchecklist.tags.id as tagsId, \n" +
    "travelchecklist.tags_group.title as groupTitle,\n" +
    "travelchecklist.tags.title as tagTitle,\n" +
    "travelchecklist.tags_group.id as groupId\n" +
    "FROM travelchecklist.tags JOIN travelchecklist.tags_group ON travelchecklist.tags.group = travelchecklist.tags_group.id;"

internal const val categoriesByTag = "SELECT DISTINCT travelchecklist.checklist_item.id AS itemId, travelchecklist.checklist_item.title AS itemTitle, travelchecklist.checklist_item.priority AS itemPriority, travelchecklist.checklist_item.optional AS itemIsOptional, travelchecklist.category.id AS categoryId, travelchecklist.category.title AS categoryTitle FROM travelchecklist.tags_to_items JOIN travelchecklist.checklist_item ON  travelchecklist.tags_to_items.item_id = travelchecklist.checklist_item.id JOIN travelchecklist.category ON travelchecklist.category.id =  travelchecklist.checklist_item.categoryId"

internal val groupTitle = "groupTitle"
internal val tagTitle = "tagTitle"
internal val groupId = "groupId"
internal val categoryId = "categoryId"
internal val categoryTitle = "categoryTitle"
internal val itemTitle = "itemTitle"
internal val itemId = "itemId"
internal val itemPriority = "itemPriority"
internal val itemIsOptional = "itemIsOptional"
internal val tagsId = "tagsId"