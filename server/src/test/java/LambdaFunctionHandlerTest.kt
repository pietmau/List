
import com.amazonaws.lambda.demo.LambdaFunctionHandler
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.S3Event
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LambdaFunctionHandlerTest {
    @Mock lateinit var event: S3Event
    @Mock lateinit var context: Context

    private lateinit var lambda: LambdaFunctionHandler

    @Before
    fun setUp() {
        lambda = LambdaFunctionHandler()
    }

    @Test
    fun when_bar_then_fobar() {
        //lambda.handleRequest(event, context)
    }

}