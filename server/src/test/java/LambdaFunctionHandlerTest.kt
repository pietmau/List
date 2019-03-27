import com.amazonaws.lambda.demo.LambdaFunctionHandler
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger
import com.amazonaws.services.lambda.runtime.events.S3Event
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class LambdaFunctionHandlerTest {
    private val event: S3Event = mockk()
    private val context: Context = mockk()
    private lateinit var lambda: LambdaFunctionHandler
    private val logger: LambdaLogger = mockk(relaxed = true)

    @Before
    fun setUp() {
        lambda = LambdaFunctionHandler()
        every { context.logger } returns logger
    }

    @Test
    fun when_bar_then_fobar() {
        lambda.handleRequest(event, context)
    }

}