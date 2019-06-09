import com.pppp.travelchecklist.server.lambdas.GetCategories
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class GetCategoriesTest {
    private val context: Context = mockk()
    private lateinit var lambda: GetCategories
    private val logger: LambdaLogger = mockk(relaxed = true)

    @Before
    fun setUp() {
        lambda = GetCategories()
        every { context.logger } returns logger
    }

    @Test
    fun when_bar_then_fobar() {
        lambda.handleRequest(emptyList(), context)
    }
}