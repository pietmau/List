import com.amazonaws.lambda.demo.ServerTag
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.GetItemRequest
import com.amazonaws.services.dynamodbv2.model.ScanRequest
import com.pietrantuono.entities.Category
import com.pietrantuono.entities.CheckListItem
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.server.categories.ServerCategory
import com.pppp.travelchecklist.server.dynamodatabase.CATEGORY_ID
import com.pppp.travelchecklist.server.dynamodatabase.CATEGORY_TITLE
import com.pppp.travelchecklist.server.dynamodatabase.DynamoTagGroups
import com.pppp.travelchecklist.server.dynamodatabase.GROUP_EXCLUSIVE
import com.pppp.travelchecklist.server.dynamodatabase.GROUP_ID
import com.pppp.travelchecklist.server.dynamodatabase.GROUP_TITLE
import com.pppp.travelchecklist.server.dynamodatabase.ITEM_ID
import com.pppp.travelchecklist.server.dynamodatabase.ITEM_TABLE_NAME
import com.pppp.travelchecklist.server.dynamodatabase.ITEM_TITLE
import com.pppp.travelchecklist.server.dynamodatabase.TAGS_TABLE_NAME
import com.pppp.travelchecklist.server.dynamodatabase.TAG_HIDDEN
import com.pppp.travelchecklist.server.dynamodatabase.TAG_ID
import com.pppp.travelchecklist.server.dynamodatabase.TAG_TITLE
import com.pppp.travelchecklist.server.pokos.ServerCheckListItem
import com.pppp.travelchecklist.server.pokos.ServerTagsGroup
import org.junit.Before
import org.junit.Test

class DynamoServer {
    private lateinit var docClient: DynamoDB
    private lateinit var client: AmazonDynamoDB

    @Before
    fun setUp() {
        client = AmazonDynamoDBClientBuilder.standard()
            .withRegion(Regions.EU_WEST_1)
            .build()
        docClient = DynamoDB(client)
    }

    @Test
    fun getTags() {
        val request = ScanRequest(TAGS_TABLE_NAME)
        val result = client.scan(request)
        val z = result.items
            .groupBy { it[GROUP_ID]?.s }
            .map { getGroup(it) }
    }

    @Test
    fun get() {
        val x = getMethodInternal()
    }

    fun getCategoriesBasedOnTags(tags: List<Tag>): List<Category> {
        return tags
            .map { it.id }
            .map { getMethodInternal(it) }
            .filterNotNull()
            .map { category: ServerCategory -> category.copy(items = mutableListOf()) to category.items.toSet() }
            .toMap()
            .map { (category: ServerCategory, items: Set<CheckListItem>) -> category.copy(items = items.toMutableList()) }
    }

    @Test
    fun getItemss() {
        val x = getMethodInternal("1")
    }

    private fun getMethodInternal(id: String): ServerCategory? {
        val table = docClient.getTable(ITEM_TABLE_NAME)
        val key = hashMapOf<String, AttributeValue>(ITEM_ID to AttributeValue().apply { s = id })
        val request = GetItemRequest()
            .withTableName(ITEM_TABLE_NAME)
            .withKey(key)
        try {
            val itemResult = client.getItem(request)
            val title = requireNotNull(itemResult.item[ITEM_TITLE]?.s)
            val categoryId = requireNotNull(itemResult.item[CATEGORY_ID]?.s)
            val categoryTitle = requireNotNull(itemResult.item[CATEGORY_TITLE]?.s)
            val item = ServerCheckListItem(title = title, categoryId = categoryId, id = id)
            return ServerCategory(title = categoryTitle, id = categoryId, items = mutableListOf(item))
        } catch (exception: Exception) {
        }
        return null
    }

    @Test
    fun getTagGroups() {
        val request = ScanRequest(TAGS_TABLE_NAME)
        val result = client.scan(request)
        val z = DynamoTagGroups().getTagsGroup()
        assert(z.isNotEmpty())
    }

    private fun getGroup(entry: Map.Entry<String?, List<MutableMap<String, AttributeValue>>>): ServerTagsGroup {
        val groupId = requireNotNull(entry.key)
        require(entry.value.isNotEmpty())
        val tags = entry.value.map { getTag(it) }
        val exclusive = entry.value.first().get(GROUP_EXCLUSIVE)?.bool ?: false
        val title = requireNotNull(entry.value.first().get(GROUP_TITLE)?.s)
        return ServerTagsGroup(title = title, tags = tags.toMutableList(), exclusive = exclusive, id = groupId)
    }

    private fun getTag(attributes: MutableMap<String, AttributeValue>): ServerTag {
        val title = requireNotNull(attributes[TAG_TITLE]?.s)
        val hidden: Boolean = attributes[TAG_HIDDEN]?.bool ?: false
        val id = requireNotNull(attributes[TAG_ID]?.s)
        return ServerTag(title, hidden, id)
    }
}