import com.amazonaws.lambda.demo.ServerTag
import com.pietrantuono.entities.Tag
import com.pppp.travelchecklist.server.dynamodatabase.CategoriesFromTags
import com.pppp.travelchecklist.server.dynamodatabase.DynamoCategoriesFromTags
import com.pppp.travelchecklist.server.dynamodatabase.utils.ItemIdsFromTags
import com.pppp.travelchecklist.server.dynamodatabase.utils.DyanmoItemIdsFromTags
import org.assertj.core.api.Assert
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class DynamoServer {
    private val itemIdsFromTags: ItemIdsFromTags = DyanmoItemIdsFromTags()
    private val categoriesFromTags: CategoriesFromTags = DynamoCategoriesFromTags()
    private val tags = mutableListOf<Tag>(ServerTag(id = "3", title = ""))

    @Test
    fun `when get items id by tags returns ids`() {
        val itemIds = itemIdsFromTags.getItemIdsFromTags(tags)
        assertThat(itemIds).hasSize(2).contains("1", "2")
    }

    @Test
    fun `get categoryes by tag`() {
        val categories = categoriesFromTags.getCategoriesBasedOnTags(tags)
        val titles = categories.map { it.title }
        assertThat(titles).hasSize(2).contains("Clothes", "Dressy clothes")
    }

    @Test
    fun `get categoryes by tag 1`() {
        val tags = mutableListOf<Tag>(ServerTag(id = "1", title = ""))
        val categories = categoriesFromTags.getCategoriesBasedOnTags(tags)
        assertThat(categories).hasSize(1)
    }
}