import com.amazonaws.Request
import com.amazonaws.lambda.demo.ServerTag
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.QueryRequest
import com.amazonaws.services.dynamodbv2.model.ScanRequest
import com.pietrantuono.entities.TagsGroup
import com.pppp.travelchecklist.server.pokos.ServerTagsGroup
import org.junit.Before
import org.junit.Test

private val GROUP_TITLE = "group_title"

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
    fun do_scan() {
        val request = ScanRequest("tag_groups")
        val result = client.scan(request)
        val z = result.items
            .groupBy { it[GROUP_TITLE]?.s }
            .map { getServerTag(it) }
    }

    private fun getServerTag(entry: Map.Entry<String?, List<MutableMap<String, AttributeValue>>>) {
        requireNotNull(entry.key)
        val group = ServerTagsGroup
    }
}