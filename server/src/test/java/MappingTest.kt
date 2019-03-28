import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger
import com.pppp.travelchecklist.server.mapping.AddMappingHandler
import com.pppp.travelchecklist.server.mapping.Mapping
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class MappingTest {
    private val context: Context = mockk()
    private lateinit var lambda: AddMappingHandler
    private val logger: LambdaLogger = mockk(relaxed = true)

    @Before
    fun setUp() {
        lambda = AddMappingHandler()
        every { context.logger } returns logger
    }

    @Test
    fun when_maps() {
        try {
            lambda.handleRequest(Mapping("Luggage tags", "All"), context)
        } catch (ex: Exception) {
            ex.localizedMessage
        }
    }
}