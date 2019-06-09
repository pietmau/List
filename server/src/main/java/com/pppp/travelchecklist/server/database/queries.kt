internal const val getTagsWithGroup = "SELECT  travelchecklist.tags.id as tagsId, \n" +
    "travelchecklist.tags_group.title as groupTitle,\n" +
    "travelchecklist.tags.title as tagTitle,\n" +
    "travelchecklist.tags_group.id as groupId\n" +
    "FROM travelchecklist.tags JOIN travelchecklist.tags_group ON travelchecklist.tags.group = travelchecklist.tags_group.id;"

internal const val getCategoriesByTag = "SELECT travelchecklist.checklist_item.id AS itemId,\n" +
    "travelchecklist.checklist_item.title AS itemTitle,\n" +
    "travelchecklist.checklist_item.priority AS itemPriority,\n" +
    "travelchecklist.checklist_item.optional AS itemIsOptional,\n" +
    "travelchecklist.category.id AS categoryId,\n" +
    "travelchecklist.category.title AS categoryTitle \n" +
    "FROM travelchecklist.tags_to_items JOIN travelchecklist.checklist_item\n" +
    "ON  travelchecklist.tags_to_items.item_id = travelchecklist.checklist_item.id\n" +
    "JOIN travelchecklist.category ON travelchecklist.category.id =  travelchecklist.checklist_item.category_id\n" +
    "WHERE"




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