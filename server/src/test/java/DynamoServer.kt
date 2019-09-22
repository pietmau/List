import com.amazonaws.lambda.demo.ServerTag
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.server.dynamodatabase.CategoriesFromTags
import com.pppp.travelchecklist.server.dynamodatabase.DynamoCategoriesFromTags
import com.pppp.travelchecklist.server.dynamodatabase.utils.ItemIdsFromTags
import com.pppp.travelchecklist.server.dynamodatabase.utils.DyanmoItemIdsFromTags
import org.junit.Before
import org.junit.Test

class DynamoServer {
    private val itemIdsFromTags: ItemIdsFromTags = DyanmoItemIdsFromTags()
    private val categoriesFromTags: CategoriesFromTags = DynamoCategoriesFromTags()
    private val tags = mutableListOf<Tag>(ServerTag(id = "3", title = ""))

    @Before
    fun setUp() {

    }

    @Test
    fun `when get items id by tags returns ids`() {

        val itemIds = itemIdsFromTags.getItemIdsFromTags(tags)
    }

    @Test
    fun `get categoryes by tag`() {
        val categories = categoriesFromTags.getCategoriesBasedOnTags(tags)
    }

}